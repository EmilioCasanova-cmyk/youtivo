package com.youtube.androidauto.browser.car

import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template
import com.youtube.androidauto.browser.R

/**
 * Pantalla de Android Auto que muestra plantillas (templates).
 * Muestra mensaje de seguridad y acciones de navegación.
 * El WebView real se usa en MainBrowserActivity (phone/Automotive OS).
 */
class WebViewTemplateScreen(
    private val carContext: androidx.car.app.CarContext,
) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val message = carContext.getString(R.string.safety_disclaimer)
        val actionStrip =
            ActionStrip.Builder()
                .addAction(
                    Action.Builder()
                        .setTitle(carContext.getString(R.string.action_home))
                        .setOnClickListener { /* Navegar a home - abrir Activity si es posible */ }
                        .build(),
                )
                .addAction(
                    Action.Builder()
                        .setTitle(carContext.getString(R.string.action_back))
                        .setOnClickListener { /* Back */ }
                        .build(),
                )
                .addAction(
                    Action.Builder()
                        .setTitle(carContext.getString(R.string.action_reload))
                        .setOnClickListener { /* Reload */ }
                        .build(),
                )
                .build()

        return MessageTemplate.Builder(message)
            .setTitle(carContext.getString(R.string.app_name))
            .setHeaderAction(Action.APP_ICON)
            .setActionStrip(actionStrip)
            .build()
    }
}
