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

    /** Pausa simple de todos los elementos video. */
    const val PAUSE_SIMPLE = "document.querySelectorAll('video').forEach(v => v.pause());"

    /** Parche para forzar la reproducción automática cuando se navega. */
    const val AUTO_PLAY_PATCH = """
        (function() {
            setInterval(() => {
                const playButton = document.querySelector('.ytp-play-button[aria-label="Reproducir"]');
                if (playButton && !window.vehicleIsMoving) {
                    playButton.click();
                }
            }, 2000);
        })();
    """

    /** Oculta elementos visuales molestos o anuncios conocidos. */
    const val CSS_AD_BLOCK = """
        (function() {
            const style = document.createElement('style');
            style.innerHTML = `
                .ad-container, .ad-div, .video-ads, .ytp-ad-progress-list { display: none !important; }
                #masthead-ad, ytd-ad-slot-renderer { display: none !important; }
            `;
            document.head.appendChild(style);
        })();
    """
}
