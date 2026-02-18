package com.youtube.androidauto.browser.car;

/**
 * Servicio principal de Android Auto. Expone la app como Car App
 * y crea la sesión que muestra la pantalla del navegador (plantillas).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/youtube/androidauto/browser/car/YouTubeBrowserCarAppService;", "Landroidx/car/app/CarAppService;", "()V", "createHostValidator", "Landroidx/car/app/validation/HostValidator;", "onCreateSession", "Landroidx/car/app/Session;", "app_debug"})
public final class YouTubeBrowserCarAppService extends androidx.car.app.CarAppService {
    
    public YouTubeBrowserCarAppService() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.car.app.validation.HostValidator createHostValidator() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.car.app.Session onCreateSession() {
        return null;
    }
}