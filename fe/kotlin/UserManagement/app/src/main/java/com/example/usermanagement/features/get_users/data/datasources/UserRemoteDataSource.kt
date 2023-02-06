package com.example.usermanagement.features.get_users.data.datasources

import androidx.lifecycle.MutableLiveData
import com.example.usermanagement.features.get_users.data.models.UserModel
import io.reactivex.rxjava3.core.Observable

abstract class UserRemoteDataSource {
    abstract fun getUsers(): MutableLiveData<List<UserModel>>
}

class UserRemoteDataSourceImpl: UserRemoteDataSource () {
    override fun getUsers(): MutableLiveData<List<UserModel>> {
        TODO("Not yet implemented")
    }
}