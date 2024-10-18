package com.fsociety.routing.request

import com.fsociety.model.User
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserRequest(
    val username: String,
    val password: String
) {
    companion object

    fun toModel(): User {
        return User(
            id = UUID.randomUUID(),
            username = username,
            password = password,
        )
    }
}