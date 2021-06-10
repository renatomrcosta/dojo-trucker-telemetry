package com.xunfosechunfos.dojotruckertelemetry

import com.amazonaws.services.kinesis.AmazonKinesis
import com.amazonaws.services.kinesis.model.GetRecordsRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.ByteBuffer

@Component
class SendMessageJob(
    private val kinesis: AmazonKinesis,
    @Value("\${kinesis.stream.name}") streamName: String,
    @Value("\${kinesis.stream.shards}") shards: Int,
) {
    init {

        kinesis.putRecord(
            streamName,
            ByteBuffer.wrap("""
                {"myFunJson": "some message"}
            """.trimIndent().toByteArray()),
            "0"
        )
    }

    init {
        runBlocking {
            delay(10_000)
            println("consuming stream")
            val shardIterator = kinesis.getShardIterator(streamName, "0", "LATEST")
            val records = kinesis.getRecords(GetRecordsRequest().withShardIterator(shardIterator.shardIterator))
            records.records.asFlow().collect {
                println(it.data)
                println(it.data.toString())
            }
        }
    }
}
