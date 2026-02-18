package com.youtube.androidauto.browser.car

import androidx.car.app.Session

/**
 * Sesión de la app en Android Auto. Crea la pantalla raíz
 * (template-based) con controles de navegación y estado del vehículo.
 */
class YouTubeBrowserSession : Session() {
    override fun onCreateScreen(intent: android.content.Intent): androidx.car.app.Screen {
        return WebViewTemplateScreen(carContext)
    }
}
