package com.fsociety.routing

import com.fsociety.routing.request.UserRequest
import com.fsociety.service.UserService
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.userRoute(
    userService: UserService
) {
    post {
        val userRequest = call.receive<UserRequest>()
        val createdUser = userService.save(
            user = userRequest.toModel()
        ) ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.respond(HttpStatusCode.Created, createdUser.toResponse())
    }

    authenticate {
        get {
            val users = userService.findAll()
            call.respond(message = users.map { it.toResponse() }, status = HttpStatusCode.OK)
        }

        get("/{id}") {
            val id: String = call.parameters["id"]
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val foundUser = userService.findById(id = UUID.fromString(id))
                ?: return@get call.respond(HttpStatusCode.NotFound)

            if (foundUser.username != extractPrincipalUsername(call)) {
                return@get call.respond(HttpStatusCode.NotFound)
            }

            call.respond(foundUser.toResponse())
        }
    }
}

fun extractPrincipalUsername(call: RoutingCall): String? {
    return call.principal<JWTPrincipal>()
        ?.payload
        ?.getClaim("username")
        ?.asString()
}
