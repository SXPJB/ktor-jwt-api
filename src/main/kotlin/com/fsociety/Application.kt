package com.fsociety


import com.fsociety.plugins.configureSecurity
import com.fsociety.plugins.configureSerialization
import com.fsociety.repository.UserRepository
import com.fsociety.routing.configureRouting
import com.fsociety.service.JwtService
import com.fsociety.service.UserService
import io.ktor.server.application.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val userRepository = UserRepository()
    val userService = UserService(userRepository)
    val jwtService = JwtService(this, userService)

    configureSerialization()
    configureSecurity(jwtService)
    configureRouting(userService, jwtService)
}
