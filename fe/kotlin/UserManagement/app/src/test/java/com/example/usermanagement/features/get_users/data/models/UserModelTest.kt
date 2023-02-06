package com.example.usermanagement.features.get_users.data.models

import com.example.usermanagement.features.get_users.domain.entities.UserEntity
import com.example.usermanagement.fixtures.readFromFile
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class UserModelTest {
    private val userModel = UserModel(uuid = UUID.fromString("75c46e40-7098-11ed-9a71-a91a125af53c"), lastName = "Doe", firstName = "John")

    @Test
    fun userModelExtendsUser() {
        assertThat(userModel, instanceOf(UserEntity::class.java))
    }

    @Test
    fun shouldReturnValidModelFromExpectedJson() {
        val expectedJson = readFromFile("user.json")
        val sut = UserModel.fromJSON(expectedJson)
        assertEquals(userModel, sut)
    }

    @Test
    fun shouldReturnExpectedStringFromToJson() {
        val expectedResult = readFromFile("user.json")
        assertEquals(expectedResult, userModel.toJson())
    }
}