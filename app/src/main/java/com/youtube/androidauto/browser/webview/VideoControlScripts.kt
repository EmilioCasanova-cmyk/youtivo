package com.youtube.androidauto.browser.webview

/**
 * Scripts de JavaScript inyectados en el WebView para controlar
 * la reproducción de video según el estado del vehículo.
 */
object VideoControlScripts {
    /** Pausa todos los videos y marca el vehículo como en movimiento. */
    const val PAUSE_ALL_VIDEOS = """
        (function() {
            const videos = document.querySelectorAll('video');
            videos.forEach(video => {
                video.pause();
                video.addEventListener('play', function handler(e) {
                    if (window.vehicleIsMoving) {
                        e.preventDefault();
                        video.pause();
                    }
                }, { capture: true });
            });
            window.vehicleIsMoving = true;
        })();
    """

    /** Permite la reproducción de video (vehículo estacionado). */
    const val ALLOW_VIDEO_PLAYBACK = """
        (function() {
            window.vehicleIsMoving = false;
        })();
    """
}
