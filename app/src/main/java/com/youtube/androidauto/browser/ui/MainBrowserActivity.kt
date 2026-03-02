package com.youtube.androidauto.browser.ui

import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.youtube.androidauto.browser.R
import com.youtube.androidauto.browser.databinding.ActivityMainBrowserBinding
import com.youtube.androidauto.browser.error.ErrorHandler
import com.youtube.androidauto.browser.navigation.NavigationController
import com.youtube.androidauto.browser.safety.SafetyController
import com.youtube.androidauto.browser.storage.SessionCookieManager
import com.youtube.androidauto.browser.vehicle.VehicleStateMonitor
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

    private lateinit var binding: ActivityMainBrowserBinding
    private lateinit var navigationController: NavigationController
    private lateinit var cookieManager: SessionCookieManager
    private lateinit var safetyController: SafetyController
    private lateinit var audioManager: AudioManager
    private lateinit var audioFocusRequest: AudioFocusRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioManager = getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager
        cookieManager = SessionCookieManager(binding.webView)

        navigationController = NavigationController(binding.webView)
        safetyController =
            SafetyController(
                vehicleMonitor = vehicleStateMonitor,
                webViewManager = binding.webView,
                scope = lifecycleScope,
            )

        setupToolbar()
        setupWebViewErrors()
        setupWebViewLoadingListener()
        safetyController.startMonitoring()

        if (savedInstanceState == null) {
            binding.loadingProgress.visibility = View.VISIBLE
            binding.webView.loadYouTube()
        }
    }

    private fun setupWebViewLoadingListener() {
        binding.webView.onPageLoaded = {
            binding.loadingProgress.visibility = View.GONE
        }
    }

    private fun setupToolbar() {
        binding.btnBack.setOnClickListener {
            if (!navigationController.goBack()) {
                Toast.makeText(this, getString(R.string.action_back), Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnForward.setOnClickListener {
            if (!navigationController.goForward()) {
                Toast.makeText(this, getString(R.string.action_forward), Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnHome.setOnClickListener {
            navigationController.goHome()
        }
        binding.btnReload.setOnClickListener {
            navigationController.reload()
        }
    }

    private fun setupWebViewErrors() {
        binding.webView.onPageError = { errorCode, _ ->
            val message = ErrorHandler.handleWebViewError(errorCode)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Solicitar Audio Focus para reproducción de media
        audioFocusRequest =
            AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build(),
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
