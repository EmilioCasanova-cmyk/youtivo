package com.youtube.androidauto.browser.webview;

/**
 * Overlay que cubre el WebView cuando el vehículo está en movimiento.
 * Muestra mensaje de seguridad y evita que el usuario vea el video.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/youtube/androidauto/browser/webview/VideoBlockerOverlay;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "messageText", "Landroid/widget/TextView;", "hide", "", "isVisible", "", "show", "app_debug"})
public final class VideoBlockerOverlay extends android.widget.FrameLayout {
    @org.jetbrains.annotations.NotNull()
    private final android.widget.TextView messageText = null;
    
    public VideoBlockerOverlay(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public final void show() {
    }
    
    public final void hide() {
    }
    
    public final boolean isVisible() {
        return false;
    }
}