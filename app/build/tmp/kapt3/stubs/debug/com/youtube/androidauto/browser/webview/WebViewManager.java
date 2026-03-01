package com.youtube.androidauto.browser.webview;

/**
 * Gestor del WebView configurado para YouTube y seguridad vial.
 * Habilita JavaScript, DOM storage, reproduce medios sin gesto,
 * y aplica restricciones de seguridad.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020\u0012J\u0006\u0010%\u001a\u00020\u0012J\u0006\u0010&\u001a\u00020\u0012J\u0006\u0010\'\u001a\u00020\u0012J\b\u0010(\u001a\u00020\u0012H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000RN\u0010\u000b\u001a6\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0010\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u0006*"}, d2 = {"Lcom/youtube/androidauto/browser/webview/WebViewManager;", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "audioManager", "Landroid/media/AudioManager;", "onPageError", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "errorCode", "", "description", "", "getOnPageError", "()Lkotlin/jvm/functions/Function2;", "setOnPageError", "(Lkotlin/jvm/functions/Function2;)V", "onPageLoaded", "Lkotlin/Function0;", "getOnPageLoaded", "()Lkotlin/jvm/functions/Function0;", "setOnPageLoaded", "(Lkotlin/jvm/functions/Function0;)V", "createChromeClient", "Landroid/webkit/WebChromeClient;", "createWebViewClient", "Landroid/webkit/WebViewClient;", "injectVideoController", "allowPlayback", "", "loadYouTube", "notifyAudioStarted", "notifyAudioStopped", "pauseAllVideos", "setupSettings", "Companion", "app_debug"})
@android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
public final class WebViewManager extends android.webkit.WebView {
    @org.jetbrains.annotations.NotNull()
    private final android.media.AudioManager audioManager = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.String, kotlin.Unit> onPageError;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onPageLoaded;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOME_URL = "https://www.youtube.com";
    @org.jetbrains.annotations.NotNull()
    public static final com.youtube.androidauto.browser.webview.WebViewManager.Companion Companion = null;
    
    public WebViewManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    private final void setupSettings() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.String, kotlin.Unit> getOnPageError() {
        return null;
    }
    
    public final void setOnPageError(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.String, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnPageLoaded() {
        return null;
    }
    
    public final void setOnPageLoaded(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    private final android.webkit.WebViewClient createWebViewClient() {
        return null;
    }
    
    private final android.webkit.WebChromeClient createChromeClient() {
        return null;
    }
    
    /**
     * Carga la página principal de YouTube.
     */
    public final void loadYouTube() {
    }
    
    /**
     * Inyecta el script que permite o bloquea video según estado del vehículo.
     */
    public final void injectVideoController(boolean allowPlayback) {
    }
    
    /**
     * Pausa todos los elementos video de la página.
     */
    public final void pauseAllVideos() {
    }
    
    /**
     * Notifica que el audio ha comenzado a reproducirse (para Audio Focus).
     */
    public final void notifyAudioStarted() {
    }
    
    /**
     * Notifica que el audio ha pausado (libera Audio Focus).
     */
    public final void notifyAudioStopped() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/youtube/androidauto/browser/webview/WebViewManager$Companion;", "", "()V", "HOME_URL", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}