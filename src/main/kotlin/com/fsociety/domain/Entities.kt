package com.fsociety.domain

import org.jetbrains.exposed.sql.Table

object UserEntity : Table("user") {
    val id = uuid("id")
    val username = varchar("username", 50)
    val password = varchar("password", 50)
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}