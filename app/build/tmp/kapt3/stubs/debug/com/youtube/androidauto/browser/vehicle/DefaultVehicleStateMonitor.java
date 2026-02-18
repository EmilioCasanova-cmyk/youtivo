package com.youtube.androidauto.browser.vehicle;

/**
 * Implementación que emite estado cada 500 ms.
 * En entorno sin CarContext usa UNKNOWN (conservador).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/youtube/androidauto/browser/vehicle/DefaultVehicleStateMonitor;", "Lcom/youtube/androidauto/browser/vehicle/VehicleStateMonitor;", "carContext", "", "(Ljava/lang/Object;)V", "getCurrentState", "Lcom/youtube/androidauto/browser/vehicle/VehicleState;", "observeVehicleState", "Lkotlinx/coroutines/flow/Flow;", "Companion", "app_debug"})
public final class DefaultVehicleStateMonitor implements com.youtube.androidauto.browser.vehicle.VehicleStateMonitor {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object carContext = null;
    public static final float SPEED_THRESHOLD_KMH = 0.5F;
    private static final long POLLING_INTERVAL_MS = 500L;
    @org.jetbrains.annotations.NotNull()
    public static final com.youtube.androidauto.browser.vehicle.DefaultVehicleStateMonitor.Companion Companion = null;
    
    public DefaultVehicleStateMonitor(@org.jetbrains.annotations.Nullable()
    java.lang.Object carContext) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.youtube.androidauto.browser.vehicle.VehicleState> observeVehicleState() {
        return null;
    }
    
    private final com.youtube.androidauto.browser.vehicle.VehicleState getCurrentState() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/youtube/androidauto/browser/vehicle/DefaultVehicleStateMonitor$Companion;", "", "()V", "POLLING_INTERVAL_MS", "", "SPEED_THRESHOLD_KMH", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}