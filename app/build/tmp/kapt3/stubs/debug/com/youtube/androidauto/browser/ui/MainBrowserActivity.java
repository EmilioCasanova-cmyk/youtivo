package com.youtube.androidauto.browser.ui;

/**
 * Actividad principal con WebView para YouTube.
 * Usada en teléfono y en Android Automotive OS.
 * Integra navegación, bloqueo de video por estado del vehículo y cookies.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001aH\u0014J\b\u0010\u001e\u001a\u00020\u001aH\u0014J\b\u0010\u001f\u001a\u00020\u001aH\u0002J\b\u0010 \u001a\u00020\u001aH\u0002J\b\u0010!\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/youtube/androidauto/browser/ui/MainBrowserActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "audioManager", "Landroid/media/AudioManager;", "cookieManager", "Lcom/youtube/androidauto/browser/storage/SessionCookieManager;", "loadingProgress", "Landroid/widget/ProgressBar;", "navigationController", "Lcom/youtube/androidauto/browser/navigation/NavigationController;", "overlay", "Lcom/youtube/androidauto/browser/webview/VideoBlockerOverlay;", "safetyController", "Lcom/youtube/androidauto/browser/safety/SafetyController;", "vehicleStateMonitor", "Lcom/youtube/androidauto/browser/vehicle/VehicleStateMonitor;", "getVehicleStateMonitor", "()Lcom/youtube/androidauto/browser/vehicle/VehicleStateMonitor;", "setVehicleStateMonitor", "(Lcom/youtube/androidauto/browser/vehicle/VehicleStateMonitor;)V", "webView", "Lcom/youtube/androidauto/browser/webview/WebViewManager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "setupToolbar", "setupWebViewErrors", "setupWebViewLoadingListener", "app_debug"})
public final class MainBrowserActivity extends androidx.appcompat.app.AppCompatActivity {
    @javax.inject.Inject()
    public com.youtube.androidauto.browser.vehicle.VehicleStateMonitor vehicleStateMonitor;
    private com.youtube.androidauto.browser.webview.WebViewManager webView;
    private com.youtube.androidauto.browser.webview.VideoBlockerOverlay overlay;
    private com.youtube.androidauto.browser.navigation.NavigationController navigationController;
    private com.youtube.androidauto.browser.storage.SessionCookieManager cookieManager;
    private com.youtube.androidauto.browser.safety.SafetyController safetyController;
    private android.widget.ProgressBar loadingProgress;
    private android.media.AudioManager audioManager;
    private android.media.AudioFocusRequest audioFocusRequest;
    
    public MainBrowserActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.youtube.androidauto.browser.vehicle.VehicleStateMonitor getVehicleStateMonitor() {
        return null;
    }
    
    public final void setVehicleStateMonitor(@org.jetbrains.annotations.NotNull()
    com.youtube.androidauto.browser.vehicle.VehicleStateMonitor p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupWebViewLoadingListener() {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupWebViewErrors() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
}