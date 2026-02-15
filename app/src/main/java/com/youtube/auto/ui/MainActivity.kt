package com.youtube.auto.ui

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Configuration
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.PowerManager
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.youtube.auto.R
import com.youtube.auto.service.BackgroundPlaybackService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private var playbackService: BackgroundPlaybackService? = null
    private var serviceBound = false
    private var wakeLock: PowerManager.WakeLock? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BackgroundPlaybackService.LocalBinder
            playbackService = binder.getService()
            serviceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            playbackService = null
            serviceBound = false
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        setupWebView()
        setupFullscreen()
        bindPlaybackService()
        acquireWakeLock()

        // Cargar YouTube
        loadYouTube()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.apply {
            settings.apply {
                // Habilitar JavaScript (necesario para YouTube)
                javaScriptEnabled = true
                
                // Configuraciones para reproducción de video
                mediaPlaybackRequiresUserGesture = false
                domStorageEnabled = true
                databaseEnabled = true
                
                // Configuraciones de caché
                cacheMode = WebSettings.LOAD_DEFAULT
                // `setAppCacheEnabled` y `setAppCachePath` fueron removidos en APIs recientes.
                // El WebView moderno gestiona la caché automáticamente.
                
                // Configuraciones de rendimiento
                loadsImagesAutomatically = true
                blockNetworkImage = false
                mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                
                // User agent de desktop para mejor compatibilidad
                userAgentString = "Mozilla/5.0 (Linux; Android 14; SM-S918B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
                
                // Permitir contenido multimedia
                allowContentAccess = true
                allowFileAccess = true
            }

            // Habilitar reproducción en segundo plano
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            
            // Cliente WebView personalizado
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    progressBar.visibility = View.GONE
                    injectBackgroundPlaybackScript()
                }

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    val url = request?.url?.toString() ?: return false
                    
                    // Manejar URLs de YouTube
                    return when {
                        url.contains("youtube.com") || url.contains("youtu.be") -> {
                            view?.loadUrl(url)
                            true
                        }
                        else -> false
                    }
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    progressBar.visibility = View.GONE
                }
            }

            // Cliente Chrome para fullscreen
            webChromeClient = object : WebChromeClient() {
                private var customView: View? = null
                private var customViewCallback: CustomViewCallback? = null

                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                    customView = view
                    customViewCallback = callback
                    // Entrar en modo fullscreen
                }

                override fun onHideCustomView() {
                    customViewCallback?.onCustomViewHidden()
                    customView = null
                }

                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    progressBar.progress = newProgress
                }
            }

            // Interfaz JavaScript para comunicación
            addJavascriptInterface(WebAppInterface(this@MainActivity), "AndroidApp")
            
            // Mantener WebView activo en segundo plano
            keepScreenOn = true
        }
    }

    private fun injectBackgroundPlaybackScript() {
        // Script JavaScript para habilitar reproducción en segundo plano
        val script = """
            (function() {
                // Prevenir pausa cuando la página pierde foco
                document.addEventListener('visibilitychange', function(e) {
                    if (document.hidden) {
                        // Mantener video reproduciéndose
                        var videos = document.querySelectorAll('video');
                        videos.forEach(function(video) {
                            video.play().catch(function() {});
                        });
                    }
                }, true);
                
                // Interceptar eventos de pausa
                window.addEventListener('blur', function(e) {
                    e.stopImmediatePropagation();
                }, true);
                
                // Mantener audio activo
                var AudioContext = window.AudioContext || window.webkitAudioContext;
                if (AudioContext) {
                    var audioCtx = new AudioContext();
                    if (audioCtx.state === 'suspended') {
                        audioCtx.resume();
                    }
                }
                
                // Prevenir que YouTube pause el video
                Object.defineProperty(document, 'hidden', {
                    get: function() { return false; },
                    configurable: true
                });
                
                Object.defineProperty(document, 'visibilityState', {
                    get: function() { return 'visible'; },
                    configurable: true
                });
                
                // Notificar a la app Android
                if (window.AndroidApp) {
                    window.AndroidApp.onPageLoaded();
                }
            })();
        """.trimIndent()
        
        webView.evaluateJavascript(script, null)
    }

    private fun setupFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.let {
            it.hide(WindowInsetsCompat.Type.systemBars())
            it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        
        // Pantalla completa inmersiva
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.systemBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    private fun bindPlaybackService() {
        val intent = Intent(this, BackgroundPlaybackService::class.java)
        ContextCompat.startForegroundService(this, intent)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun acquireWakeLock() {
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(
            PowerManager.PARTIAL_WAKE_LOCK,
            "YouTubeAuto::PlaybackWakeLock"
        ).apply {
            setReferenceCounted(false)
            acquire(10*60*1000L) // 10 minutos
        }
    }

    private fun loadYouTube() {
        val url = intent?.data?.toString() ?: "https://m.youtube.com"
        webView.loadUrl(url)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.toString()?.let { url ->
            if (url.contains("youtube") || url.contains("youtu.be")) {
                webView.loadUrl(url)
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Manejar cambios de orientación
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                if (webView.canGoBack()) {
                    webView.goBack()
                    return true
                }
            }
            KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE,
            KeyEvent.KEYCODE_MEDIA_PLAY,
            KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                togglePlayback()
                return true
            }
            KeyEvent.KEYCODE_MEDIA_NEXT -> {
                webView.evaluateJavascript("document.querySelector('.ytp-next-button')?.click();", null)
                return true
            }
            KeyEvent.KEYCODE_MEDIA_PREVIOUS -> {
                webView.evaluateJavascript("history.back();", null)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun togglePlayback() {
        webView.evaluateJavascript("""
            var video = document.querySelector('video');
            if (video) {
                if (video.paused) {
                    video.play();
                } else {
                    video.pause();
                }
            }
        """.trimIndent(), null)
    }

    override fun onPause() {
        super.onPause()
        // No pausar WebView para mantener reproducción en segundo plano
        // Evitamos llamar a `webView.onPause()` que puede detener la reproducción
        webView.resumeTimers()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
        webView.resumeTimers()
        injectBackgroundPlaybackScript()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (serviceBound) {
            unbindService(serviceConnection)
        }
        wakeLock?.release()
        webView.destroy()
    }

    private fun ensureBatteryOptimizationDisabled() {
        try {
            val pm = getSystemService(POWER_SERVICE) as android.os.PowerManager
            val packageName = packageName
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !pm.isIgnoringBatteryOptimizations(packageName)) {
                val intent = android.content.Intent(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                intent.data = android.net.Uri.parse("package:$packageName")
                startActivity(intent)
            }
        } catch (e: Exception) {
            // No hacer nada si falla; se puede instruir al usuario manualmente
        }
    }

    // Interfaz JavaScript
    class WebAppInterface(private val context: Context) {
        @android.webkit.JavascriptInterface
        fun onPageLoaded() {
            // Página cargada
        }

        @android.webkit.JavascriptInterface
        fun onVideoPlaying() {
            // Video reproduciéndose
        }

        @android.webkit.JavascriptInterface
        fun onVideoPaused() {
            // Video pausado
        }

        @android.webkit.JavascriptInterface
        fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
