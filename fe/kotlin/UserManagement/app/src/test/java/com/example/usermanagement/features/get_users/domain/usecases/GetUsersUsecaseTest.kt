package com.example.usermanagement.features.get_users.domain.usecases

import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.computations.result
import com.example.core.Failure
import com.example.core.NoParams
import com.example.usermanagement.features.get_users.data.models.UserModel
import com.example.usermanagement.features.get_users.domain.entities.UserEntity
import com.example.usermanagement.features.get_users.domain.repositories.GetUsersRepository
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class GetUsersUsecaseTest {

    @Mock
    private lateinit var mockUser: UserModel

    @Test
    fun shouldGetUsersFromRepository() {
            val results = Observable.fromArray(listOf(mockUser))

            val mockRepository = mock<GetUsersRepository>()

            `when`(mockRepository.getUsers()).thenReturn(Either.Right(listOf(mockUser)))

            val myObjectUnderTest = GetUsersUsecase(mockRepository)

            // ...when the string is returned from the object under test...
            myObjectUnderTest.invoke(NoParams(), onResult = { assertEquals(result, results.blockingFirst()) })

    }
}
