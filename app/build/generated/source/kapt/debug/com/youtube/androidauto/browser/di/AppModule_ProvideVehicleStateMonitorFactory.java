package com.youtube.androidauto.browser.di;

import com.youtube.androidauto.browser.vehicle.VehicleStateMonitor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideVehicleStateMonitorFactory implements Factory<VehicleStateMonitor> {
  @Override
  public VehicleStateMonitor get() {
    return provideVehicleStateMonitor();
  }

  public static AppModule_ProvideVehicleStateMonitorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static VehicleStateMonitor provideVehicleStateMonitor() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideVehicleStateMonitor());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideVehicleStateMonitorFactory INSTANCE = new AppModule_ProvideVehicleStateMonitorFactory();
  }
}
