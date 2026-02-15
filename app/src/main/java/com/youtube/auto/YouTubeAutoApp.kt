package com.youtube.auto

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class YouTubeAutoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Canal para reproducción en segundo plano
            val playbackChannel = NotificationChannel(
                CHANNEL_PLAYBACK,
                "Reproducción de YouTube",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notificación para reproducción en segundo plano"
                setShowBadge(false)
                enableLights(false)
                enableVibration(false)
            }
            notificationManager.createNotificationChannel(playbackChannel)

            // Canal para controles de medios
            val mediaChannel = NotificationChannel(
                CHANNEL_MEDIA,
                "Controles de Medios",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Controles de reproducción de medios"
                setShowBadge(false)
            }
            notificationManager.createNotificationChannel(mediaChannel)
        }
    }

    companion object {
        lateinit var instance: YouTubeAutoApp
            private set

        const val CHANNEL_PLAYBACK = "youtube_playback_channel"
        const val CHANNEL_MEDIA = "youtube_media_channel"
        const val NOTIFICATION_ID_PLAYBACK = 1001
        const val NOTIFICATION_ID_MEDIA = 1002
    }
}
