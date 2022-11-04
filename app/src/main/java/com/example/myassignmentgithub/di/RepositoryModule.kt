package com.example.myassignmentgithub.di

import com.example.myassignmentgithub.datasource.RemoteUserDatasource
import com.example.myassignmentgithub.repositories.UserRepository
import com.example.myassignmentgithub.repositories.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(remoteUserDatasource: RemoteUserDatasource): UserRepository {
        return UserRepositoryImpl(remoteUserDatasource)
    }
}
