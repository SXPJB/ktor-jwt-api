package com.fsociety.service

import com.fsociety.common.model.User
import com.fsociety.repository.UserPostgresqlRepository

class UserPostgresqlService(
    private val repository: UserPostgresqlRepository
) {
    suspend fun save(user: User) = repository.save(user)
    suspend fun findAll() = repository.findAll()
}