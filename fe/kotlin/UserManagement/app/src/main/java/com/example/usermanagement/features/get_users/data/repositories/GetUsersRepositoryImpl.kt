package com.example.usermanagement.features.get_users.data.repositories

import arrow.core.Either
import com.example.core.Failure
import com.example.core.platform.NetworkInfo
import com.example.usermanagement.features.get_users.data.datasources.UserLocalDataSource
import com.example.usermanagement.features.get_users.data.datasources.UserRemoteDataSource
import com.example.usermanagement.features.get_users.data.models.UserModel
import com.example.usermanagement.features.get_users.domain.repositories.GetUsersRepository
import javax.inject.Inject

class GetUsersRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
    private val connectionChecker: NetworkInfo
    ): GetUsersRepository {

    override fun getUsers(): Either<Failure, List<UserModel>> {
        val isConnected = connectionChecker.isConnected().blockingFirst()
        return Either.Left(Failure.ServerFailure)
    }
}