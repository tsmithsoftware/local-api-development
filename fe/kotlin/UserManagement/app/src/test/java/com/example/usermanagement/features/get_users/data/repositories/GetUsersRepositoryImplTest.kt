package com.example.usermanagement.features.get_users.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.core.platform.NetworkInfo
import com.example.usermanagement.features.get_users.data.datasources.UserLocalDataSource
import com.example.usermanagement.features.get_users.data.datasources.UserRemoteDataSource
import com.example.usermanagement.features.get_users.data.models.UserModel
import com.example.usermanagement.features.get_users.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import java.util.UUID

@RunWith(MockitoJUnitRunner::class)
class GetUsersRepositoryImplTest {
    @Mock
    private lateinit var localDataSource: UserLocalDataSource

    @Mock
    private lateinit var remoteDataSource: UserRemoteDataSource

    @Mock
    private lateinit var networkInfo: NetworkInfo

    @Mock
    private lateinit var mockUser: UserModel

    private var mockLiveData: MutableLiveData<List<UserModel>> = MutableLiveData<List<UserModel>>()

    private fun setupSuccessfulRemoteObserver() {
        `when`(remoteDataSource.getUsers()).thenReturn(mockLiveData)
    }

    private fun setupSuccessfulLocalObserver() {
        `when`(localDataSource.getUsers()).thenReturn(mockLiveData)
    }

    private val uuid = UUID.randomUUID()

    @Before
    fun setUp() {
        `when`(mockUser.uuid).thenReturn(uuid)
        `when`(mockUser.firstName).thenReturn("Bill")
        `when`(mockUser.lastName).thenReturn("Smith")
    }

    fun setOnline() {
        `when`(networkInfo.isConnected()).thenReturn(Observable.fromArray(true))
    }

    fun setOffline() {
        `when`(networkInfo.isConnected()).thenReturn(Observable.fromArray(false))
    }

    @Test
    fun testDeviceIsOnline() {
        setOnline()
        val repository = GetUsersRepositoryImpl(localDataSource, remoteDataSource, networkInfo)
        setupSuccessfulRemoteObserver()
        repository.getUsers()
        verify(networkInfo).isConnected()
    }

    @Test
    fun shouldCallRemoteDataSourceWhenRemoteCallSuccessful() {
        val results = Observable.fromArray(true)
        `when`(networkInfo.isConnected()).thenReturn(results)
        setupSuccessfulRemoteObserver()
        val repository = GetUsersRepositoryImpl(localDataSource, remoteDataSource, networkInfo)
        repository.getUsers()
        verify(remoteDataSource).getUsers()
    }

    @Test
    fun shouldReturnDataFromRemoteSourceWhenRemoteCallSuccessful() {
        val results = Observable.fromArray(true)
        `when`(networkInfo.isConnected()).thenReturn(results)
        setupSuccessfulRemoteObserver()
        val successfulData = Observable.fromArray(listOf(UserEntity(
            uuid,
            "Bill",
            "Smith"
        )))
        val repository = GetUsersRepositoryImpl(localDataSource, remoteDataSource, networkInfo)
        val result = repository.getUsers()
        assertEquals(successfulData.blockingFirst(), result)
    }

    @Test
    fun shouldReturnDataFromLocalDataSourceWheConnectionNotAvailable() {
        `when`(networkInfo.isConnected()).thenReturn(Observable.fromArray(false))
        setupSuccessfulLocalObserver()
        val successfulData = Observable.fromArray(listOf(UserEntity(
            uuid,
            "Bill",
            "Smith"
        )))
        val repository = GetUsersRepositoryImpl(localDataSource, remoteDataSource, networkInfo)
        val result = repository.getUsers()
        assertEquals(successfulData.blockingFirst(), result)
        verifyNoInteractions(remoteDataSource)
        verify(localDataSource.getUsers())
    }
}