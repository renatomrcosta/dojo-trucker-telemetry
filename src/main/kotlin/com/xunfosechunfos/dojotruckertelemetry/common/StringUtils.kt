package com.xunfosechunfos.dojotruckertelemetry.common


fun String.parseToRows(): List<String> = this.split("\n")
    .filter { it.isNotBlank() }
