package com.timsmithsoftware.integration_tests.models

import com.timsmithsoftware.integration_tests.models.database.User
import org.junit.jupiter.api.Test
import org.springframework.util.Assert

class DatabaseConnectionTests {
    val conn = DatabaseConnection()
    @Test
    fun getUsersReturnsNonEmptyUserList() {
        val users: List<User> = conn.getUsers()
        Assert.isTrue(users.isNotEmpty(), "Connection has not returned any users!")
        Assert.isTrue(users[0].firstName.isNotEmpty(), "Connection has returned empty users!")
    }
}