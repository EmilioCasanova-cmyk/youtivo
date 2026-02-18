package com.youtube.androidauto.browser.car;

/**
 * Pantalla de Android Auto que muestra plantillas (templates).
 * Muestra mensaje de seguridad y acciones de navegación.
 * El WebView real se usa en MainBrowserActivity (phone/Automotive OS).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/youtube/androidauto/browser/car/WebViewTemplateScreen;", "Landroidx/car/app/Screen;", "carContext", "Landroidx/car/app/CarContext;", "(Landroidx/car/app/CarContext;)V", "onGetTemplate", "Landroidx/car/app/model/Template;", "app_debug"})
public final class WebViewTemplateScreen extends androidx.car.app.Screen {
    @org.jetbrains.annotations.NotNull()
    private final androidx.car.app.CarContext carContext = null;
    
    public WebViewTemplateScreen(@org.jetbrains.annotations.NotNull()
    androidx.car.app.CarContext carContext) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.car.app.model.Template onGetTemplate() {
        return null;
    }
}