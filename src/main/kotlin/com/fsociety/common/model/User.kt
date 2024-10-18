package com.fsociety.common.model

import com.fsociety.common.response.UserResponse
import java.util.UUID

data class User(
    val id: UUID,
    val username: String,
    val password: String,
) {
    fun toResponse(): UserResponse {
        return UserResponse(
            id = id,
            username = username,
            password = password,
        )
    }
}
