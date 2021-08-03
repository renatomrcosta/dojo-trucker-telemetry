package com.xunfosechunfos.dojotruckertelemetry.config

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaAdminConfiguration(
    @Value("\${telemetry.kafka.bootstrap_address}")
    private val bootstrapAddress: String,
    @Value("\${telemetry.kafka.topic.name}")
    private val topicName: String,
) {

//    @Bean
//    fun kafkaAdmin(): KafkaAdmin = KafkaAdmin(mapOf(
//        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress
//    ))

    @Bean
    fun topic(): NewTopic = TopicBuilder.name("topicName").build()
}

//@Configuration
//class KafkaProducerConfig(
//    @Value("\${telemetry.kafka.bootstrap_address}")
//    private val bootstrapAddress: String,
//) {
//    @Bean
//    fun producerFactory(): ProducerFactory<String, String> = DefaultKafkaProducerFactory(
//        mapOf(
//            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
//            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class,
//            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class,
//        )
//    )
//
//    @Bean
//    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>): KafkaTemplate<String, String> =
//        KafkaTemplate(producerFactory())
//}
//
//@Configuration
//class KafkaReceiverConfig(
//    @Value("\${telemetry.kafka.bootstrap_address}")
//    private val bootstrapAddress: String,
//) {
//    @Bean
//    fun consumerFactory(): ConsumerFactory<String, String> = DefaultKafkaConsumerFactory(
//        mapOf(
//            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
//            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringSerializer::class,
//            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringSerializer::class,
//        )
//    )
//
//    @Bean
//    fun kafkaListenerContainerFactory(
//        consumerFactory: ConsumerFactory<String, String>,
//    ): ConcurrentKafkaListenerContainerFactory<String, String> =
//        ConcurrentKafkaListenerContainerFactory<String, String>().apply { this.consumerFactory = consumerFactory }
//
//}
