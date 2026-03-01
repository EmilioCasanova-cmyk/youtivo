package com.youtube.androidauto.browser.ui

import android.media.AudioFocusRequest
import android.media.AudioAttributes
import android.media.AudioManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.youtube.androidauto.browser.R
import com.youtube.androidauto.browser.error.ErrorHandler
import com.youtube.androidauto.browser.navigation.NavigationController
import com.youtube.androidauto.browser.safety.SafetyController
import com.youtube.androidauto.browser.storage.SessionCookieManager
import com.youtube.androidauto.browser.vehicle.VehicleStateMonitor
import com.youtube.androidauto.browser.webview.VideoBlockerOverlay
import com.youtube.androidauto.browser.webview.WebViewManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Actividad principal con WebView para YouTube.
 * Usada en teléfono y en Android Automotive OS.
 * Integra navegación, bloqueo de video por estado del vehículo y cookies.
 */
@AndroidEntryPoint
class MainBrowserActivity : AppCompatActivity() {
    @Inject
    lateinit var vehicleStateMonitor: VehicleStateMonitor

    private lateinit var webView: WebViewManager
    private lateinit var overlay: VideoBlockerOverlay
    private lateinit var navigationController: NavigationController
    private lateinit var cookieManager: SessionCookieManager
    private lateinit var safetyController: SafetyController
    private lateinit var loadingProgress: ProgressBar
    private lateinit var audioManager: AudioManager
    private lateinit var audioFocusRequest: AudioFocusRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_browser)

        webView = findViewById(R.id.webView)
        loadingProgress = findViewById(R.id.loadingProgress)
        audioManager = getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager
        cookieManager = SessionCookieManager(webView)

        navigationController = NavigationController(webView)
        safetyController =
            SafetyController(
                vehicleMonitor = vehicleStateMonitor,
                webViewManager = webView,
                scope = lifecycleScope,
            )

        setupToolbar()
        setupWebViewErrors()
        setupWebViewLoadingListener()
        safetyController.startMonitoring()

        if (savedInstanceState == null) {
            loadingProgress.visibility = View.VISIBLE
            webView.loadYouTube()
        }
    }

    private fun setupWebViewLoadingListener() {
        webView.onPageLoaded = {
            loadingProgress.visibility = View.GONE
        }
    }


    private fun setupToolbar() {
        findViewById<android.widget.ImageButton>(R.id.btnBack).setOnClickListener {
            if (!navigationController.goBack()) {
                Toast.makeText(this, getString(R.string.action_back), Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<android.widget.ImageButton>(R.id.btnForward).setOnClickListener {
            if (!navigationController.goForward()) {
                Toast.makeText(this, getString(R.string.action_forward), Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<android.widget.ImageButton>(R.id.btnHome).setOnClickListener {
            navigationController.goHome()
        }
        findViewById<android.widget.ImageButton>(R.id.btnReload).setOnClickListener {
            navigationController.reload()
        }
    }

    private fun setupWebViewErrors() {
        webView.onPageError = { errorCode, _ ->
            val message = ErrorHandler.handleWebViewError(errorCode)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Solicitar Audio Focus para reproducción de media
        audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            )
            .setAcceptsDelayedFocusGain(true)
            .build()
        audioManager.requestAudioFocus(audioFocusRequest)
    }

    override fun onPause() {
        super.onPause()
        // Liberar Audio Focus al pausar la actividad
        if (::audioFocusRequest.isInitialized) {
            audioManager.abandonAudioFocusRequest(audioFocusRequest)
        }
        cookieManager.saveCookies()
    }
}
