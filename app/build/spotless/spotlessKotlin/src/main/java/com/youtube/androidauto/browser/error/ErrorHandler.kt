package com.youtube.androidauto.browser.error

import android.webkit.WebViewClient

/**
 * Convierte códigos de error del WebView en mensajes para el usuario.
 */
object ErrorHandler {
    fun handleWebViewError(errorCode: Int): String =
        when (errorCode) {
            WebViewClient.ERROR_HOST_LOOKUP -> "Error de conexión. Verifica tu internet."
            WebViewClient.ERROR_CONNECT -> "Error de conexión. Verifica tu internet."
            WebViewClient.ERROR_TIMEOUT -> "Timeout. Intenta recargar la página."
            WebViewClient.ERROR_UNKNOWN -> "Error desconocido. Reinicia la app."
            else -> "Error al cargar la página."
        }

    fun handleSensorError(): String = "Sensores del vehículo no disponibles. Video bloqueado por seguridad."
}
