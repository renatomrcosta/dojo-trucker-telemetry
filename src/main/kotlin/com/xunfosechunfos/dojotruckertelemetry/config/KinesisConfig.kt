package com.xunfosechunfos.dojotruckertelemetry.config

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.kinesis.AmazonKinesis
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KinesisConfig {
    @Bean
    fun kinesis(
        @Value("\${kinesis.stream.name}") streamName: String,
        @Value("\${kinesis.stream.shards}") shards: Int,
    ): AmazonKinesis = AmazonKinesisClientBuilder
        .standard()
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration("http://localhost:4566", Regions.DEFAULT_REGION.getName())
        )
        .build().also {
            try {
                it.createStream(streamName, shards)
            } catch (e: Exception) {
                println("Stream $streamName exists")
            }
        }
}
