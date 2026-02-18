package com.youtube.androidauto.browser.di

import com.youtube.androidauto.browser.vehicle.DefaultVehicleStateMonitor
import com.youtube.androidauto.browser.vehicle.VehicleStateMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideVehicleStateMonitor(): VehicleStateMonitor = DefaultVehicleStateMonitor(carContext = null)
}
