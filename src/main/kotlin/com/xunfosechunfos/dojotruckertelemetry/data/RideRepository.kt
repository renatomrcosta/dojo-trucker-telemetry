package com.xunfosechunfos.dojotruckertelemetry.data

import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RideRepository(
    private val driverRepository: DriverRepository,
    private val truckRepository: TruckRepository,
) {
    val rideRepository by lazy {
        (0..99).map { idx ->
            randomTelemetry(truckRepository.repository[idx] to driverRepository.repository[idx])
        }
    }

    private fun randomTelemetry(pair: Pair<UUID, UUID>) {
        // TODO add telemetry based on existing state
    }
}
