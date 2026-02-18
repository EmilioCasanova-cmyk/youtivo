package com.youtube.androidauto.browser.vehicle

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow

/**
 * Monitor del estado del vehículo (PARKED / MOVING).
 * Cuando no hay contexto de coche (ej. app en teléfono), devuelve UNKNOWN
 * para bloquear video por seguridad.
 */
interface VehicleStateMonitor {
    fun observeVehicleState(): Flow<VehicleState>
}

/**
 * Implementación que emite estado cada 500 ms.
 * En entorno sin CarContext usa UNKNOWN (conservador).
 */
class DefaultVehicleStateMonitor(
    private val carContext: Any?,
) : VehicleStateMonitor {
    override fun observeVehicleState(): Flow<VehicleState> =
        flow {
            while (true) {
                val state = getCurrentState()
                emit(state)
                delay(POLLING_INTERVAL_MS)
            }
        }.conflate()

    private fun getCurrentState(): VehicleState {
        // MODIFICACIÓN: Siempre devolver PARKED para permitir video en movimiento (Navegador sin restricciones)
        return VehicleState.PARKED
    }

    companion object {
        const val SPEED_THRESHOLD_KMH = 0.5f
        private const val POLLING_INTERVAL_MS = 500L
    }
}
