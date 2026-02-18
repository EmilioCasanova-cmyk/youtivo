package com.youtube.androidauto.browser.safety

import com.youtube.androidauto.browser.vehicle.VehicleState
import com.youtube.androidauto.browser.vehicle.VehicleStateMonitor
import com.youtube.androidauto.browser.webview.VideoBlockerOverlay
import com.youtube.androidauto.browser.webview.WebViewManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

/**
 * Controlador de seguridad que sincroniza el estado del vehículo
 * con el bloqueo/desbloqueo de video en el WebView.
 */
class SafetyController(
    private val vehicleMonitor: VehicleStateMonitor,
    private val webViewManager: WebViewManager,
    private val overlay: VideoBlockerOverlay,
    private val scope: CoroutineScope,
) {
    fun startMonitoring() {
        vehicleMonitor.observeVehicleState()
            .onEach { state ->
                when (state) {
                    VehicleState.PARKED -> enableVideo()
                    VehicleState.MOVING -> blockVideo()
                    VehicleState.UNKNOWN -> blockVideo()
                }
            }
            .launchIn(scope)
    }

    private fun enableVideo() {
        overlay.hide()
        webViewManager.injectVideoController(allowPlayback = true)
        Timber.d("Safety: video enabled (PARKED)")
    }

    private fun blockVideo() {
        webViewManager.injectVideoController(allowPlayback = false)
        webViewManager.pauseAllVideos()
        overlay.show()
        Timber.d("Safety: video blocked (MOVING/UNKNOWN)")
    }
}
