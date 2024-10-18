package com.fsociety.repository

import com.fsociety.model.User
import java.util.UUID

class UserRepository : CRUDRepository<User, UUID> {

    private val users = mutableListOf<User>()

    override fun findAll(): List<User> {
        return users
    }

    override fun findByUsername(username: String): User? {
        return users.firstOrNull { it.username == username }
    }

    override fun save(entity: User): User {
        users.add(entity)
        return entity.copy()
    }

    override fun findById(id: UUID): User? {
        return users.firstOrNull { it.id == id }
    }
}