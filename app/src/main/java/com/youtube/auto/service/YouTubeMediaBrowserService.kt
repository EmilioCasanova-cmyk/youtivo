package com.youtube.auto.service

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat
import com.youtube.auto.R

class YouTubeMediaBrowserService : MediaBrowserServiceCompat() {

    private var mediaSession: MediaSessionCompat? = null

    override fun onCreate() {
        super.onCreate()
        
        mediaSession = MediaSessionCompat(this, "YouTubeAutoMediaBrowser").apply {
            setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or 
                     MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
            
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() {
                    updatePlaybackState(PlaybackStateCompat.STATE_PLAYING)
                }

                override fun onPause() {
                    updatePlaybackState(PlaybackStateCompat.STATE_PAUSED)
                }

                override fun onStop() {
                    updatePlaybackState(PlaybackStateCompat.STATE_STOPPED)
                }
            })

            setSessionToken(sessionToken)
            isActive = true
        }
        
        sessionToken = mediaSession?.sessionToken
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot {
        // Permitir acceso a todas las apps de Android Auto
        return BrowserRoot(MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        val mediaItems = mutableListOf<MediaBrowserCompat.MediaItem>()

        when (parentId) {
            MEDIA_ROOT_ID -> {
                // Elementos raíz del menú
                mediaItems.add(createBrowsableItem("trending", "Tendencias", "Videos populares"))
                mediaItems.add(createBrowsableItem("history", "Historial", "Videos recientes"))
                mediaItems.add(createBrowsableItem("playlists", "Listas de reproducción", "Tus playlists"))
                mediaItems.add(createBrowsableItem("search", "Buscar", "Buscar en YouTube"))
            }
            "trending" -> {
                // Aquí se cargarían videos en tendencia
                mediaItems.add(createPlayableItem("video1", "Video de ejemplo 1", "Canal 1"))
                mediaItems.add(createPlayableItem("video2", "Video de ejemplo 2", "Canal 2"))
            }
            "history" -> {
                // Historial de reproducción
                mediaItems.add(createPlayableItem("hist1", "Video reciente 1", "Canal"))
            }
            else -> {
                // Elementos por defecto
            }
        }

        result.sendResult(mediaItems)
    }

    private fun createBrowsableItem(id: String, title: String, subtitle: String): MediaBrowserCompat.MediaItem {
        val description = MediaDescriptionCompat.Builder()
            .setMediaId(id)
            .setTitle(title)
            .setSubtitle(subtitle)
            .setIconUri(android.net.Uri.parse("android.resource://${packageName}/${R.drawable.ic_folder}"))
            .build()
        
        return MediaBrowserCompat.MediaItem(description, MediaBrowserCompat.MediaItem.FLAG_BROWSABLE)
    }

    private fun createPlayableItem(id: String, title: String, artist: String): MediaBrowserCompat.MediaItem {
        val description = MediaDescriptionCompat.Builder()
            .setMediaId(id)
            .setTitle(title)
            .setSubtitle(artist)
            .setIconUri(android.net.Uri.parse("android.resource://${packageName}/${R.drawable.ic_music_note}"))
            .build()
        
        return MediaBrowserCompat.MediaItem(description, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
    }

    private fun updatePlaybackState(state: Int) {
        val playbackState = PlaybackStateCompat.Builder()
            .setState(state, 0, 1.0f)
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                PlaybackStateCompat.ACTION_PAUSE or
                PlaybackStateCompat.ACTION_PLAY_PAUSE or
                PlaybackStateCompat.ACTION_STOP
            )
            .build()
        
        mediaSession?.setPlaybackState(playbackState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaSession?.release()
    }

    companion object {
        const val MEDIA_ROOT_ID = "youtube_root"
    }
}
