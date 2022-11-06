package com.example.myassignmentgithub.datasource

import com.example.myassignmentgithub.network.GitHubUsersApi
import com.example.myassignmentgithub.network.exceptions.ApiNetworkException
import com.example.myassignmentgithub.network.exceptions.ApiUnsuccessfulException
import com.example.myassignmentgithub.model.UserShortInfo
import java.io.IOException
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val api: GitHubUsersApi
) : RemoteUserDatasource {

    override fun searchUser(queryText: String): List<UserShortInfo> {
        try {
            val response = api.searchUsers(queryText).execute()
            if (!response.isSuccessful) {
                throw ApiUnsuccessfulException()
            }
            return response.body()?.modelSearchData ?: throw ApiUnsuccessfulException()
        } catch (ioException: IOException) {
            throw ApiNetworkException(ioException)
        }
    }
    override fun getAllUsers(): List<UserShortInfo> {
        try {
            val response = api.getAllUsers().execute()
            if (!response.isSuccessful) {
                throw ApiUnsuccessfulException()
            }
            return response.body() ?: throw ApiUnsuccessfulException()
        } catch (exception: Exception) {
            throw ApiNetworkException(exception)
        }
    }
}
