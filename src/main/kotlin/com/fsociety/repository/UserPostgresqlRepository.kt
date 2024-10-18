package com.fsociety.repository

import com.fsociety.common.model.User
import com.fsociety.domain.UserEntity
import com.fsociety.domain.UserEntity.id
import com.fsociety.domain.UserEntity.password
import com.fsociety.domain.UserEntity.username
import com.fsociety.domain.db.DatabaseConfig.query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class UserPostgresqlRepository {

    suspend fun save(user: User): User = query {
        UserEntity.insert { column ->
            column[id] = user.id
            column[username] = user.username
            column[password] = user.password
        }
        return@query user
    }

    suspend fun findAll(): List<User> = query {
        UserEntity.selectAll().map(::map)
    }


    private fun map(row: ResultRow): User {
        return User(
            id = row[id],
            username = row[username],
            password = row[password]
        )
    }
}