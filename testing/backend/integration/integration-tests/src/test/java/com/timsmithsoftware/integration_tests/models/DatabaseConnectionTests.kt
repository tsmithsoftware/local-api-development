package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.SystemConfiguration
import com.timsmithsoftware.integration_tests.models.database.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.util.Assert

class DatabaseConnectionTests {
    private val conn = DatabaseConnection()

    companion object {

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            println("GetUsers - waiting for connection")
            val config = SystemConfiguration()
            config.waitForConnections()
        }
    }
    @Test
    fun getUsersReturnsNonEmptyUserList() {
        val users: List<User> = conn.getUsers()
        Assert.isTrue(users.isNotEmpty(), "Connection has not returned any users!")
        Assert.isTrue(users[0].firstName.isNotEmpty(), "Connection has returned empty users!")
    }

    @Test
    fun addThenRemoveUserRemovesAddedUser() {
        val userList = conn.getUsers()
        val userSize = userList.size
        conn.addUser(lastName = "Test", firstName = "Bill")
        assert(conn.getUsers().size == userSize + 1)
        conn.removeUser("Test", "Bill")
        assert(conn.getUsers().size == userSize)
    }
}