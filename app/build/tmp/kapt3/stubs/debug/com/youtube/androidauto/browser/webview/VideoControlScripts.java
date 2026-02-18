package com.youtube.androidauto.browser.webview;

/**
 * Scripts de JavaScript inyectados en el WebView para controlar
 * la reproducción de video según el estado del vehículo.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/youtube/androidauto/browser/webview/VideoControlScripts;", "", "()V", "ALLOW_VIDEO_PLAYBACK", "", "PAUSE_ALL_VIDEOS", "app_debug"})
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
    @org.jetbrains.annotations.NotNull()
    public static final com.youtube.androidauto.browser.webview.VideoControlScripts INSTANCE = null;
    
    private VideoControlScripts() {
        super();
    }
}