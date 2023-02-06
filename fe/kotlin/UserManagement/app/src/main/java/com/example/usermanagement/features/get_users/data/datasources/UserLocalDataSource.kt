package com.example.usermanagement.features.get_users.data.datasources

import androidx.lifecycle.MutableLiveData
import com.example.usermanagement.features.get_users.data.models.UserModel
import io.reactivex.rxjava3.core.Observable

abstract class UserLocalDataSource {
    abstract fun getUsers(): MutableLiveData<List<UserModel>>
}

class UserLocalDataSourceImpl: UserLocalDataSource() {
    override fun getUsers(): MutableLiveData<List<UserModel>> {
        TODO("Not yet implemented")
    }
}