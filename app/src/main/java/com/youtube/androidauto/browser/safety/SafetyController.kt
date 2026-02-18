package com.youtube.androidauto.browser.safety

import com.youtube.androidauto.browser.vehicle.VehicleStateMonitor
import com.youtube.androidauto.browser.webview.WebViewManager
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

/**
 * Controlador de seguridad que sincroniza el estado del vehículo
 * con el bloqueo/desbloqueo de video en el WebView.
 */
class SafetyController(
    private val vehicleMonitor: VehicleStateMonitor,
    private val webViewManager: WebViewManager,
    private val scope: CoroutineScope,
) {
    fun startMonitoring() {
        // En modo "Navegador sin Restricciones", siempre permitimos video.
        // El VehicleStateMonitor ya está forzado a PARKED, pero aquí aseguramos la llamada inicial.
        enableVideo()
    }

    private fun enableVideo() {
        webViewManager.injectVideoController(allowPlayback = true)
        Timber.d("Safety: video enabled (ALWAYS ALLOWED)")
    }

    // La función blockVideo ya no se usa.
}
