package com.youtube.androidauto.browser.ui

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_browser)

        webView = findViewById(R.id.webView)
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
        safetyController.startMonitoring()

        if (savedInstanceState == null) {
            webView.loadYouTube()
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

    override fun onPause() {
        super.onPause()
        cookieManager.saveCookies()
    }
}
