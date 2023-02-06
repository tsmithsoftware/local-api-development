package com.example.usermanagement.features.get_users.di

import com.example.core.platform.NetworkInfo
import com.example.core.platform.NetworkInfoImpl
import com.example.usermanagement.features.get_users.data.datasources.UserLocalDataSource
import com.example.usermanagement.features.get_users.data.datasources.UserLocalDataSourceImpl
import com.example.usermanagement.features.get_users.data.datasources.UserRemoteDataSource
import com.example.usermanagement.features.get_users.data.datasources.UserRemoteDataSourceImpl
import com.example.usermanagement.features.get_users.data.repositories.GetUsersRepositoryImpl
import com.example.usermanagement.features.get_users.domain.repositories.GetUsersRepository
import com.example.usermanagement.features.get_users.domain.usecases.GetUsersUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object GetUsersModule {
    @Provides
    fun provideUserLocalDataSource(): UserLocalDataSource {
        return UserLocalDataSourceImpl()
    }

    @Provides
    fun provideUserRemoteDataSource(): UserRemoteDataSource {
        return UserRemoteDataSourceImpl()
    }

    @Provides
    fun provideNetworkInfo(): NetworkInfo {
        return NetworkInfoImpl()
    }

    @Provides
    fun provideGetUsersRepository(
        localDataSource: UserLocalDataSource,
        remoteDataSource: UserRemoteDataSource,
        connectionChecker: NetworkInfo
    ): GetUsersRepository = GetUsersRepositoryImpl(localDataSource, remoteDataSource, connectionChecker)

    @Provides
    fun provideGetUsersUsecase(
        usersRepository: GetUsersRepository
    ): GetUsersUsecase = GetUsersUsecase(usersRepository)
}