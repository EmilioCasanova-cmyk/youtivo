package com.youtube.androidauto.browser.vehicle

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests del monitor de estado del vehículo.
 * DefaultVehicleStateMonitor sin CarContext devuelve UNKNOWN (conservador).
 */
class VehicleStateMonitorTest {
    @Test
    fun `when no car context then state is UNKNOWN`() =
        runTest {
            val monitor = DefaultVehicleStateMonitor(carContext = null)
            val state = monitor.observeVehicleState().first()
            assertEquals(VehicleState.UNKNOWN, state)
        }
}
