package com.example.myassignmentgithub.di

import com.example.myassignmentgithub.network.GitHubUsersApi
import com.example.myassignmentgithub.repositories.GitHubUserRepository
import com.example.myassignmentgithub.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(usersApi: GitHubUsersApi): UserRepository {
        return GitHubUserRepository(usersApi)
    }
}
