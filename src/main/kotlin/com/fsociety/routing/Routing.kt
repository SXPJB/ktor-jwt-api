package com.fsociety.routing

import com.fsociety.service.JwtService
import com.fsociety.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.parameter.parametersOf
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService by inject<UserService>()
    val jwtService by inject<JwtService> { parametersOf(this) }

    routing {
        route("/api/auth") {
            authRoute(jwtService)
        }
        route("/api/user") {
            userRoute(userService)
        }
    }
}