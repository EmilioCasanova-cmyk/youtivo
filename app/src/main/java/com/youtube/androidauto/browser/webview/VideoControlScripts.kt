package com.youtube.androidauto.browser.webview

/**
 * Scripts de JavaScript inyectados en el WebView para controlar
 * la reproducción de video según el estado del vehículo.
 */
object VideoControlScripts {
    /** Pausa todos los videos y marca el vehículo como en movimiento. Usa un listener global. */
    const val PAUSE_ALL_VIDEOS = """
        (function() {
            window.vehicleIsMoving = true;
            document.querySelectorAll('video').forEach(v => v.pause());
            
            if (!window.videoPauseListenerAdded) {
                document.addEventListener('play', function(e) {
                    if (window.vehicleIsMoving && e.target.tagName === 'VIDEO') {
                        e.target.pause();
                        console.log('Video pausado por seguridad (vehículo en movimiento)');
                    }
                }, true);
                window.videoPauseListenerAdded = true;
            }
        })();
    """

    /** Permite la reproducción de video (vehículo estacionado). */
    const val ALLOW_VIDEO_PLAYBACK = """
        (function() {
            window.vehicleIsMoving = false;
            console.log('Video permitido (vehículo detenido)');
        })();
    """

    /** Pausa simple de todos los elementos video. */
    const val PAUSE_SIMPLE = "document.querySelectorAll('video').forEach(v => v.pause());"

    /** Parche para forzar la reproducción automática cuando se navega. */
    const val AUTO_PLAY_PATCH = """
        (function() {
            if (window.autoPlayPatchAdded) return;
            window.autoPlayPatchAdded = true;
            
            setInterval(() => {
                if (window.vehicleIsMoving) return;
                
                const playButtons = [
                    '.ytp-play-button[aria-label="Reproducir"]',
                    '.ytp-large-play-button',
                    'button.ytp-play-button'
                ];
                
                for (const selector of playButtons) {
                    const btn = document.querySelector(selector);
                    if (btn && btn.offsetParent !== null && !btn.classList.contains('ytp-play-button-active')) {
                        // Solo clic si no se está ya reproduciendo (heurística básica)
                        if (btn.getAttribute('aria-label') === 'Reproducir' || btn.tagName === 'BUTTON') {
                            btn.click();
                            break;
                        }
                    }
                }
            }, 2000);
        })();
    """

    /** Oculta elementos visuales molestos o anuncios conocidos. */
    const val CSS_AD_BLOCK = """
        (function() {
            if (window.adBlockStylesAdded) return;
            window.adBlockStylesAdded = true;
            
            const style = document.createElement('style');
            style.innerHTML = `
                .ad-container, .ad-div, .video-ads, .ytp-ad-progress-list,
                #masthead-ad, ytd-ad-slot-renderer, .ytd-in-feed-ad-layout-renderer,
                .ytp-ad-overlay-container, .ytp-ad-message-container,
                ytd-promoted-sparkles-web-renderer, ytd-display-ad-render,
                #player-ads, .ytd-banner-promo-renderer { display: none !important; }
            `;
            document.head.appendChild(style);
        })();
    """
}
