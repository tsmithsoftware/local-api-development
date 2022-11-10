package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.models.database.DataSource
import com.timsmithsoftware.integration_tests.models.database.User
import com.timsmithsoftware.integration_tests.models.database.Users
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.entity.*
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection : IDatabaseConnection {

    override fun getUsers(): List<User> {
        val database = Database.connect(DataSource.source)
        return database.sequenceOf(Users).toList()
    }

    override fun removeUser(lastName: String, firstName: String) {
        val database = Database.connect(DataSource.source)
        val usersToDelete = database.sequenceOf(Users)
            .filter { it.firstName eq firstName }
            .filter { it.lastName eq lastName }
            .toList()

        for(user in usersToDelete) {
            database.delete(Users) { it.id eq user.id }
        }
    }

    override fun waitUntilAlive(): Boolean {
        return try{
            val connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/db", "user", "password")
            connection.close()
            true
        } catch (e: SQLException){
            Thread.sleep(200)
            waitUntilAlive()
        }
    }
}