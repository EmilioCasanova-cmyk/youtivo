package com.youtube.auto.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.widget.Toast

class MediaButtonReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_MEDIA_BUTTON == intent.action) {
            val keyEvent = intent.getParcelableExtra<KeyEvent>(Intent.EXTRA_KEY_EVENT)
            keyEvent?.let {
                when (it.keyCode) {
                    KeyEvent.KEYCODE_MEDIA_PLAY -> {
                        Toast.makeText(context, "Play", Toast.LENGTH_SHORT).show()
                    }
                    KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                        Toast.makeText(context, "Pause", Toast.LENGTH_SHORT).show()
                    }
                    KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE -> {
                        Toast.makeText(context, "Play/Pause", Toast.LENGTH_SHORT).show()
                    }
                    KeyEvent.KEYCODE_MEDIA_NEXT -> {
                        Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show()
                    }
                    KeyEvent.KEYCODE_MEDIA_PREVIOUS -> {
                        Toast.makeText(context, "Previous", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
