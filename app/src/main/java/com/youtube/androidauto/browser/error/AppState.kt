package com.youtube.androidauto.browser.error

/**
 * Estados de la aplicación para UI y manejo de errores.
 */
sealed class AppState {
    object Loading : AppState()

    object Ready : AppState()

    data class Error(val message: String) : AppState()

    object VehicleSensorUnavailable : AppState()
}
