package com.youtube.androidauto.browser.webview;

/**
 * Scripts de JavaScript inyectados en el WebView para controlar
 * la reproducción de video según el estado del vehículo.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/youtube/androidauto/browser/webview/VideoControlScripts;", "", "()V", "ALLOW_VIDEO_PLAYBACK", "", "AUTO_PLAY_PATCH", "CSS_AD_BLOCK", "PAUSE_ALL_VIDEOS", "PAUSE_SIMPLE", "app_debug"})
public final class VideoControlScripts {
    
    /**
     * Pausa todos los videos y marca el vehículo como en movimiento.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PAUSE_ALL_VIDEOS = "\n        (function() {\n            const videos = document.querySelectorAll(\'video\');\n            videos.forEach(video => {\n                video.pause();\n                video.addEventListener(\'play\', function handler(e) {\n                    if (window.vehicleIsMoving) {\n                        e.preventDefault();\n                        video.pause();\n                    }\n                }, { capture: true });\n            });\n            window.vehicleIsMoving = true;\n        })();\n    ";
    
    /**
     * Permite la reproducción de video (vehículo estacionado).
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ALLOW_VIDEO_PLAYBACK = "\n        (function() {\n            window.vehicleIsMoving = false;\n        })();\n    ";
    
    /**
     * Pausa simple de todos los elementos video.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PAUSE_SIMPLE = "document.querySelectorAll(\'video\').forEach(v => v.pause());";
    
    /**
     * Parche para forzar la reproducción automática cuando se navega.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AUTO_PLAY_PATCH = "\n        (function() {\n            setInterval(() => {\n                const playButton = document.querySelector(\'.ytp-play-button[aria-label=\"Reproducir\"]\');\n                if (playButton && !window.vehicleIsMoving) {\n                    playButton.click();\n                }\n            }, 2000);\n        })();\n    ";
    
    /**
     * Oculta elementos visuales molestos o anuncios conocidos.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CSS_AD_BLOCK = "\n        (function() {\n            const style = document.createElement(\'style\');\n            style.innerHTML = `\n                .ad-container, .ad-div, .video-ads, .ytp-ad-progress-list { display: none !important; }\n                #masthead-ad, ytd-ad-slot-renderer { display: none !important; }\n            `;\n            document.head.appendChild(style);\n        })();\n    ";
    @org.jetbrains.annotations.NotNull()
    public static final com.youtube.androidauto.browser.webview.VideoControlScripts INSTANCE = null;
    
    private VideoControlScripts() {
        super();
    }
}