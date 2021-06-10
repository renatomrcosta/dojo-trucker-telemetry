package com.xunfosechunfos.dojotruckertelemetry.data

import org.springframework.stereotype.Repository

@Repository
class TruckRepository : AbstractUUIDRepository("trucks.txt")
