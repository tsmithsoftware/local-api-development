package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.models.database.User
import com.timsmithsoftware.integration_tests.models.database.Users
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseConnection : IDatabaseConnection {

    override fun getUsers(): List<User> {
        val database = Database.connect(
            url = "jdbc:mysql://127.0.0.1:3306/db",
            user = "user",
            password = "password"
        )

        return database.sequenceOf(Users).toList()
    }

    override fun waitUntilAlive(): Boolean {
        // TODO Auto-generated method stub
        return true
    }
}