package com.example.usermanagement.features.get_users.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.Failure
import com.example.core.NoParams
import com.example.usermanagement.features.get_users.domain.entities.UserEntity
import com.example.usermanagement.features.get_users.domain.usecases.GetUsersUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(var getUsersUsecase: GetUsersUsecase): ViewModel() {
    var users: MutableLiveData<List<UserEntity>> = MutableLiveData()
    var failure: MutableLiveData<Failure> = MutableLiveData()

    fun loadUsers() =
        getUsersUsecase.invoke(params = NoParams(), onResult = { it.fold(::handleFailure, ::handleUserList) })

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    private fun handleUserList(movies: List<UserEntity>) {
        this.users.value = movies.map { UserEntity(it.uuid, it.lastName, it.firstName) }
    }
}