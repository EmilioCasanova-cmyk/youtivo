package com.youtube.androidauto.browser.webview

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.youtube.androidauto.browser.R

/**
 * Overlay que cubre el WebView cuando el vehículo está en movimiento.
 * Muestra mensaje de seguridad y evita que el usuario vea el video.
 */
class VideoBlockerOverlay(context: Context) : FrameLayout(context) {
    private val messageText: TextView

    init {
        setBackgroundColor(Color.BLACK)
        messageText =
            TextView(context).apply {
                text = context.getString(R.string.video_blocked_message)
                textSize = 24f
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
                setPadding(48, 48, 48, 48)
            }
        addView(
            messageText,
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT,
            ).apply { gravity = Gravity.CENTER },
        )
    }

    fun show() {
        visibility = View.VISIBLE
    }

    fun hide() {
        visibility = View.GONE
    }

    fun isVisible(): Boolean = visibility == View.VISIBLE
}
