package com.youtube.androidauto.browser.vehicle

/**
 * Estado del vehículo para control de seguridad vial.
 */
enum class VehicleState {
    /** Velocidad ≈ 0, se puede mostrar video */
    PARKED,

    /** Velocidad > umbral, solo audio o bloqueo de video */
    MOVING,

    /** Estado no disponible; comportamiento conservador: bloquear video */
    UNKNOWN,
}
