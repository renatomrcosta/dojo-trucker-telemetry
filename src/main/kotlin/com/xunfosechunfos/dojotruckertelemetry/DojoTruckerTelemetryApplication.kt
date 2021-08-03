package com.xunfosechunfos.dojotruckertelemetry

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@SpringBootApplication
@EnableKafka
class DojoTruckerTelemetryApplication

fun main(args: Array<String>) {
    runApplication<DojoTruckerTelemetryApplication>(*args)
}

@RestController
class SampleController(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${telemetry.kafka.topic.name}")
    private val topicName: String,
    private val objectMapper: ObjectMapper,
) {
    @GetMapping("/")
    fun sendMessage() {
        val truckState = TruckState(id = UUID.randomUUID(), state = "Wet sponge")
        val payload = objectMapper.writeValueAsString(truckState)
        kafkaTemplate.send(topicName, payload)
    }

    @KafkaListener(topics = ["trucks_topic"])
    fun listener(message: String) {
        println("RECEIVED MESSAGE: [$message]")
        try {
            val truckState: TruckState = objectMapper.readValue(message)
            println("State: $truckState")
        } catch (e: Exception) {
            println("oops $e")

        }
    }
}

data class TruckState(val id: UUID, val state: String)
