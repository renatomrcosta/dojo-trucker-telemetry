package com.xunfosechunfos.dojotruckertelemetry.messaging

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import java.util.UUID

class TelemetryMessaging() {
    suspend fun telemetry(): Flow<TelemetryData> = flow {

        delay(500)

    }

}

data class TelemetryData(
    val truckId: UUID,
    val driverId: UUID,
    val speed: Speed,
    val location: Point,
    val fuel: Int,
    val temperature: Temperature,
)

data class Speed(
    val unit: String = "km/h",
    val value: Int,
)

data class Point(
    val latitude: Double,
    val longitude: Double,
)

data class Temperature(
    val unit: String = "C",
    val value: Double,
)
