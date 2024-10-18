package com.fsociety.repository

interface CRUDRepository<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun findByUsername(username: String): T?
    fun save(entity: T): T
}