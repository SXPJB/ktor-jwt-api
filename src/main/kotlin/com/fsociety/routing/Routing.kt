package com.fsociety.routing

import com.fsociety.service.JwtService
import com.fsociety.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userService: UserService,
    jwtService: JwtService,
) {
    routing {
        route("/api/auth") {
            authRoute(jwtService)
        }
        route("/api/user") {
            userRoute(userService)
        }
    }
}