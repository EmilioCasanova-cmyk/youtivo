package com.youtube.androidauto.browser.safety;

/**
 * Controlador de seguridad que sincroniza el estado del vehículo
 * con el bloqueo/desbloqueo de video en el WebView.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u000b\u001a\u00020\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/youtube/androidauto/browser/safety/SafetyController;", "", "vehicleMonitor", "Lcom/youtube/androidauto/browser/vehicle/VehicleStateMonitor;", "webViewManager", "Lcom/youtube/androidauto/browser/webview/WebViewManager;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lcom/youtube/androidauto/browser/vehicle/VehicleStateMonitor;Lcom/youtube/androidauto/browser/webview/WebViewManager;Lkotlinx/coroutines/CoroutineScope;)V", "enableVideo", "", "startMonitoring", "app_debug"})
public final class SafetyController {
    @org.jetbrains.annotations.NotNull()
    private final com.youtube.androidauto.browser.vehicle.VehicleStateMonitor vehicleMonitor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.youtube.androidauto.browser.webview.WebViewManager webViewManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    public SafetyController(@org.jetbrains.annotations.NotNull()
    com.youtube.androidauto.browser.vehicle.VehicleStateMonitor vehicleMonitor, @org.jetbrains.annotations.NotNull()
    com.youtube.androidauto.browser.webview.WebViewManager webViewManager, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope) {
        super();
    }
    
    public final void startMonitoring() {
    }
    
    private final void enableVideo() {
    }
}