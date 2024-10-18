package com.fsociety.service

import com.fsociety.common.model.User
import com.fsociety.repository.UserRepository
import java.util.UUID

class UserService(
    private val repository: UserRepository
) {

    fun findAll() = repository.findAll()

    fun findById(id: UUID) = repository.findById(id)

    fun findByUsername(username: String) = repository.findByUsername(username)

    fun save(user: User): User? {
        val existingUser = repository.findByUsername(user.username)

        return if (existingUser == null) {
            repository.save(user)
        } else null
    }

}