package com.example.usermanagement.features.get_users.di

import com.example.usermanagement.features.get_users.data.repositories.GetUsersRepositoryImpl
import com.example.usermanagement.features.get_users.domain.repositories.GetUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

/**
@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(impl: GetUsersRepositoryImpl): GetUsersRepository
}
    **/