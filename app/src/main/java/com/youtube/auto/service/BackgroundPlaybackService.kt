package com.youtube.auto.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.media.session.MediaButtonReceiver
import com.youtube.auto.R
import com.youtube.auto.YouTubeAutoApp
import com.youtube.auto.ui.MainActivity

class BackgroundPlaybackService : Service() {

    private val binder = LocalBinder()
    private var mediaSession: MediaSessionCompat? = null
    private var audioManager: AudioManager? = null
    private var audioFocusRequest: AudioFocusRequest? = null
    private var audioFocusGranted = false

    inner class LocalBinder : Binder() {
        fun getService(): BackgroundPlaybackService = this@BackgroundPlaybackService
    }

    override fun onCreate() {
        super.onCreate()
        initMediaSession()
        initAudioManager()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        MediaButtonReceiver.handleIntent(mediaSession, intent)
        
        // Iniciar como servicio en primer plano
        startForeground(
            YouTubeAutoApp.NOTIFICATION_ID_PLAYBACK,
            createNotification()
        )
        
        return START_STICKY
    }

    private fun initMediaSession() {
        mediaSession = MediaSessionCompat(this, "YouTubeAutoSession").apply {
            setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or 
                     MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
            
            // Callback para controles de medios
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() {
                    super.onPlay()
                    requestAudioFocus()
                    updatePlaybackState(PlaybackStateCompat.STATE_PLAYING)
                }

                override fun onPause() {
                    super.onPause()
                    updatePlaybackState(PlaybackStateCompat.STATE_PAUSED)
                }

                override fun onStop() {
                    super.onStop()
                    abandonAudioFocus()
                    updatePlaybackState(PlaybackStateCompat.STATE_STOPPED)
                }

                override fun onSkipToNext() {
                    super.onSkipToNext()
                    // Enviar comando a WebView
                }

                override fun onSkipToPrevious() {
                    super.onSkipToPrevious()
                    // Enviar comando a WebView
                }

                override fun onSeekTo(pos: Long) {
                    super.onSeekTo(pos)
                }
            })

            // Nota: `setSessionToken` no es un método de MediaSessionCompat.
            // El token está disponible en `sessionToken` para exponer desde una MediaBrowserService si es necesario.
            
            // Estado inicial
            isActive = true
            updatePlaybackState(PlaybackStateCompat.STATE_NONE)
        }
    }

    private fun initAudioManager() {
        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener { focusChange ->
                    when (focusChange) {
                        AudioManager.AUDIOFOCUS_GAIN -> {
                            audioFocusGranted = true
                        }
                        AudioManager.AUDIOFOCUS_LOSS,
                        AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                            audioFocusGranted = false
                        }
                    }
                }
                .build()
        }
    }

    private fun requestAudioFocus(): Boolean {
        audioFocusGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioFocusRequest?.let {
                audioManager?.requestAudioFocus(it) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
            } ?: false
        } else {
            @Suppress("DEPRECATION")
            audioManager?.requestAudioFocus(
                { focusChange ->
                    when (focusChange) {
                        AudioManager.AUDIOFOCUS_GAIN -> audioFocusGranted = true
                        AudioManager.AUDIOFOCUS_LOSS -> audioFocusGranted = false
                    }
                },
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
            ) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
        }
        return audioFocusGranted
    }

    private fun abandonAudioFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioFocusRequest?.let {
                audioManager?.abandonAudioFocusRequest(it)
            }
        } else {
            @Suppress("DEPRECATION")
            audioManager?.abandonAudioFocus(null)
        }
        audioFocusGranted = false
    }

    private fun updatePlaybackState(state: Int) {
        val playbackState = PlaybackStateCompat.Builder()
            .setState(state, 0, 1.0f)
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                PlaybackStateCompat.ACTION_PAUSE or
                PlaybackStateCompat.ACTION_PLAY_PAUSE or
                PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or
                PlaybackStateCompat.ACTION_STOP
            )
            .build()
        
        mediaSession?.setPlaybackState(playbackState)
        updateNotification()
    }

    fun updateMetadata(title: String, artist: String, album: String? = null) {
        val metadata = MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
            .build()
        
        mediaSession?.setMetadata(metadata)
        updateNotification()
    }

    private fun createNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val playPauseIntent = PendingIntent.getBroadcast(
            this, 1,
            Intent(this, MediaButtonReceiver::class.java).apply {
                action = PlaybackStateCompat.ACTION_PLAY_PAUSE.toString()
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, YouTubeAutoApp.CHANNEL_PLAYBACK)
            .setContentTitle("YouTube Auto")
            .setContentText("Reproducción en segundo plano activa")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setSilent(true)
            .addAction(R.drawable.ic_play_pause, "Play/Pause", playPauseIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession?.sessionToken)
                    .setShowActionsInCompactView(0)
            )
            .build()
    }

    private fun updateNotification() {
        val notification = createNotification()
        startForeground(YouTubeAutoApp.NOTIFICATION_ID_PLAYBACK, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        abandonAudioFocus()
        mediaSession?.release()
    }
}
