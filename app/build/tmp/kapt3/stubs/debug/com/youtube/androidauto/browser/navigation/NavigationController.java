package com.youtube.androidauto.browser.navigation;

/**
 * Controlador de navegación web: atrás, adelante, recargar, inicio.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\b\u0010\u0010\u001a\u0004\u0018\u00010\tJ\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u000eJ\u0006\u0010\u0013\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tJ\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u0002J\u0006\u0010\u0017\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/youtube/androidauto/browser/navigation/NavigationController;", "", "webView", "Landroid/webkit/WebView;", "(Landroid/webkit/WebView;)V", "currentIndex", "", "history", "", "", "addToHistory", "", "url", "canGoBack", "", "canGoForward", "getCurrentUrl", "goBack", "goForward", "goHome", "navigateToUrl", "normalizeUrl", "input", "reload", "app_debug"})
public final class NavigationController {
    @org.jetbrains.annotations.NotNull()
    private final android.webkit.WebView webView = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> history = null;
    private int currentIndex = -1;
    
    public NavigationController(@org.jetbrains.annotations.NotNull()
    android.webkit.WebView webView) {
        super();
    }
    
    public final void navigateToUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final boolean goBack() {
        return false;
    }
    
    public final boolean goForward() {
        return false;
    }
    
    public final void reload() {
    }
    
    public final void goHome() {
    }
    
    public final boolean canGoBack() {
        return false;
    }
    
    public final boolean canGoForward() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentUrl() {
        return null;
    }
    
    private final void addToHistory(java.lang.String url) {
    }
    
    private final java.lang.String normalizeUrl(java.lang.String input) {
        return null;
    }
}