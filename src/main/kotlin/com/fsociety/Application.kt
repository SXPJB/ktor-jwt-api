package com.fsociety

import com.fsociety.domain.db.DatabaseConfig
import com.fsociety.plugins.configureKoin
import com.fsociety.plugins.configureSecurity
import com.fsociety.plugins.configureSerialization
import com.fsociety.routing.configureRouting
import io.ktor.server.application.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseConfig.init(environment)
    configureKoin()
    configureSerialization()
    configureSecurity()
    configureRouting()
}