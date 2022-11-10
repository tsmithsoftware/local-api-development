package com.timsmithsoftware.integration_tests.models.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Users : Table<User>("users") {
    val id = int("userId").primaryKey().bindTo { it.id }
    val firstName = varchar("firstName").bindTo { it.firstName }
}

interface User : Entity<User> {
    companion object : Entity.Factory<User> ()
    val id: Int
    val firstName: String
}