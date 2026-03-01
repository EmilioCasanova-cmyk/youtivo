package com.youtube.androidauto.browser.car

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

/**
 * Servicio principal de Android Auto. Expone la app como Car App
 * y crea la sesión que muestra la pantalla del navegador (plantillas).
 */
class YouTubeBrowserCarAppService : CarAppService() {
    override fun createHostValidator(): HostValidator = HostValidator.ALLOW_ALL_HOSTS_VALIDATOR

    override fun onCreateSession(): Session = YouTubeBrowserSession()
}
