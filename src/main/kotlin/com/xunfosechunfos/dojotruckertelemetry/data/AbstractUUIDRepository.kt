package com.xunfosechunfos.dojotruckertelemetry.data

import com.xunfosechunfos.dojotruckertelemetry.common.parseToRows
import org.springframework.util.ResourceUtils
import java.util.*
import java.util.logging.Logger

abstract class AbstractUUIDRepository(private val resourcePath: String) {
    private val repository = HashSet<UUID>()

    fun put(item: UUID) {
        repository.add(item)
    }

    fun getOrNull(item: UUID) = if (repository.contains(item)) item else null

    init {
        loadFromFile()
    }

    private fun loadFromFile() {
        logger.info("Loading from resource file $resourcePath")
        ResourceUtils.getFile("classpath:$resourcePath")
            .readText()
            .parseToRows()
            .forEach { put(UUID.fromString(it)) }
        logger.info("${repository.size} Ids loaded successfully")
    }

    companion object {
        private val logger = Logger.getLogger(DriverRepository::class.qualifiedName)
    }
}
