package com.youtube.androidauto.browser.storage;

/**
 * Gestión de cookies para persistir sesión de YouTube entre reinicios.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\tR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/youtube/androidauto/browser/storage/SessionCookieManager;", "", "webView", "Landroid/webkit/WebView;", "(Landroid/webkit/WebView;)V", "cookieManager", "Landroid/webkit/CookieManager;", "kotlin.jvm.PlatformType", "clearCookies", "", "onResult", "Lkotlin/Function1;", "", "getCookiesForDomain", "", "domain", "saveCookies", "app_debug"})
public final class SessionCookieManager {
    @org.jetbrains.annotations.Nullable()
    private final android.webkit.WebView webView = null;
    private final android.webkit.CookieManager cookieManager = null;
    
    public SessionCookieManager(@org.jetbrains.annotations.Nullable()
    android.webkit.WebView webView) {
        super();
    }
    
    public final void saveCookies() {
    }
    
    public final void clearCookies(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onResult) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCookiesForDomain(@org.jetbrains.annotations.NotNull()
    java.lang.String domain) {
        return null;
    }
    
    public SessionCookieManager() {
        super();
    }
}