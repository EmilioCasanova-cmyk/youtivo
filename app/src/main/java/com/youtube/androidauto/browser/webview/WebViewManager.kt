package com.youtube.androidauto.browser.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.util.AttributeSet
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber

private const val YOUTUBE_HOME = "https://www.youtube.com"
private const val DESKTOP_USER_AGENT =
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
        "Chrome/120.0.0.0 Safari/537.36"

/**
 * Gestor del WebView configurado para YouTube y seguridad vial.
 * Habilita JavaScript, DOM storage, reproduce medios sin gesto,
 * y aplica restricciones de seguridad.
 */
@SuppressLint("SetJavaScriptEnabled")
class WebViewManager(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : WebView(context, attrs, defStyleAttr) {
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    init {
        setupSettings()
        webViewClient = createWebViewClient()
        webChromeClient = createChromeClient()
        setBackgroundColor(Color.BLACK)
    }

    private fun setupSettings() {
        settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            mediaPlaybackRequiresUserGesture = false
            allowFileAccess = false
            allowContentAccess = false
            allowFileAccessFromFileURLs = false
            allowUniversalAccessFromFileURLs = false
            setSupportZoom(true)
            loadWithOverviewMode = true
            useWideViewPort = true
            userAgentString = DESKTOP_USER_AGENT
            mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
            cacheMode = WebSettings.LOAD_DEFAULT
        }
        CookieManager.getInstance().apply {
            setAcceptCookie(true)
            setAcceptThirdPartyCookies(this@WebViewManager, true)
        }
    }

    var onPageError: ((errorCode: Int, description: String?) -> Unit)? = null

    private fun createWebViewClient(): WebViewClient =
        object : WebViewClient() {
            override fun onPageFinished(
                view: WebView?,
                url: String?,
            ) {
                super.onPageFinished(view, url)
                Timber.d("Page finished: %s", url)
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?,
            ) {
                Timber.e("WebView error %d: %s @ %s", errorCode, description, failingUrl)
                onPageError?.invoke(errorCode, description)
            }
        }

    private fun createChromeClient(): WebChromeClient =
        object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView?,
                newProgress: Int,
            ) {
                // Opcional: notificar progreso a la UI
            }
        }

    /** Carga la página principal de YouTube. */
    fun loadYouTube() {
        loadUrl(YOUTUBE_HOME)
    }

    /** Inyecta el script que permite o bloquea video según estado del vehículo. */
    fun injectVideoController(allowPlayback: Boolean) {
        val script =
            if (allowPlayback) {
                VideoControlScripts.ALLOW_VIDEO_PLAYBACK
            } else {
                VideoControlScripts.PAUSE_ALL_VIDEOS
            }
        evaluateJavascript(script, null)
    }

    /** Pausa todos los elementos video de la página. */
    fun pauseAllVideos() {
        evaluateJavascript(
            "document.querySelectorAll('video').forEach(v => v.pause());",
            null,
        )
    }

    /** Notifica que el audio ha comenzado a reproducirse (para Audio Focus). */
    fun notifyAudioStarted() {
        audioManager.playSoundEffect(AudioManager.FX_FOCUS_NAVIGATION_UP, 0f)
        Timber.d("Audio reproduction started - notified AudioManager")
    }

    /** Notifica que el audio ha pausado (libera Audio Focus). */
    fun notifyAudioStopped() {
        Timber.d("Audio reproduction stopped")
    }

    companion object {
        const val HOME_URL = YOUTUBE_HOME
    }
}
