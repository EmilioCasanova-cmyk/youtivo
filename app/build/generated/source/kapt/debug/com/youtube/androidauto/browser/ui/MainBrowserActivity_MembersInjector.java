package com.youtube.androidauto.browser.ui;

import com.youtube.androidauto.browser.vehicle.VehicleStateMonitor;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainBrowserActivity_MembersInjector implements MembersInjector<MainBrowserActivity> {
  private final Provider<VehicleStateMonitor> vehicleStateMonitorProvider;

  public MainBrowserActivity_MembersInjector(
      Provider<VehicleStateMonitor> vehicleStateMonitorProvider) {
    this.vehicleStateMonitorProvider = vehicleStateMonitorProvider;
  }

  public static MembersInjector<MainBrowserActivity> create(
      Provider<VehicleStateMonitor> vehicleStateMonitorProvider) {
    return new MainBrowserActivity_MembersInjector(vehicleStateMonitorProvider);
  }

  @Override
  public void injectMembers(MainBrowserActivity instance) {
    injectVehicleStateMonitor(instance, vehicleStateMonitorProvider.get());
  }

  @InjectedFieldSignature("com.youtube.androidauto.browser.ui.MainBrowserActivity.vehicleStateMonitor")
  public static void injectVehicleStateMonitor(MainBrowserActivity instance,
      VehicleStateMonitor vehicleStateMonitor) {
    instance.vehicleStateMonitor = vehicleStateMonitor;
  }
}
