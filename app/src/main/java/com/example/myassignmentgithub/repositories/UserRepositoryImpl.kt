package com.example.myassignmentgithub.repositories

import com.example.myassignmentgithub.datasource.RemoteUserDatasource
import com.example.myassignmentgithub.model.UserShortInfo
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteUserDatasource: RemoteUserDatasource
) : UserRepository {

    override fun searchUser(query: String) : List<UserShortInfo> {
        val response = runBlocking {
            remoteUserDatasource.searchUser(query)
        }
        return response
    }

    override fun getAllUsers(): List<UserShortInfo> {
        return runBlocking {
            remoteUserDatasource.getAllUsers()
        }
    }
}