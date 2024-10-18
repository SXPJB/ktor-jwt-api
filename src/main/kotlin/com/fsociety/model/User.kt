package com.fsociety.model

import com.fsociety.routing.response.UserResponse
import java.util.UUID

data class User(
    val id: UUID,
    val username: String,
    val password: String,
) {
    companion object

    fun toResponse(): UserResponse {
        return UserResponse(
            id = id,
            username = username,
            password = password,
        )
    }
}
