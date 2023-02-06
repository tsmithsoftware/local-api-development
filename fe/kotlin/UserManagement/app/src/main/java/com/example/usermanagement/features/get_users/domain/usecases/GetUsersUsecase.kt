package com.example.usermanagement.features.get_users.domain.usecases

import arrow.core.Either
import com.example.core.Failure
import com.example.core.NoParams
import com.example.core.UseCase
import com.example.usermanagement.features.get_users.data.models.UserModel
import com.example.usermanagement.features.get_users.domain.repositories.GetUsersRepository
import javax.inject.Inject

class GetUsersUsecase @Inject constructor(var repository: GetUsersRepository): UseCase<List<UserModel>, NoParams>() {
    override suspend fun run(params: NoParams): Either<Failure, List<UserModel>> = repository.getUsers()
}
