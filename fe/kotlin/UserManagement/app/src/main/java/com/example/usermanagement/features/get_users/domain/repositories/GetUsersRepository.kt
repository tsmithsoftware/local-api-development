package com.example.usermanagement.features.get_users.domain.repositories

import arrow.core.Either
import com.example.core.Failure
import com.example.usermanagement.features.get_users.data.models.UserModel

interface GetUsersRepository {
    fun getUsers(): Either<Failure, List<UserModel>>
}