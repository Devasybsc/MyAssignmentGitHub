package com.example.myassignmentgithub.di

import com.example.myassignmentgithub.datasource.RemoteUserDataSourceImpl
import com.example.myassignmentgithub.datasource.RemoteUserDatasource
import com.example.myassignmentgithub.network.GitHubUsersApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteUserDatasource(usersApi: GitHubUsersApi): RemoteUserDatasource {
        return RemoteUserDataSourceImpl(usersApi)
    }
}
