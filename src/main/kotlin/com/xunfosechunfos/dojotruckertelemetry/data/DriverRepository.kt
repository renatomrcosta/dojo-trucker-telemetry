package com.xunfosechunfos.dojotruckertelemetry.data

import org.springframework.stereotype.Repository

@Repository
class DriverRepository : AbstractUUIDRepository("drivers.txt")
