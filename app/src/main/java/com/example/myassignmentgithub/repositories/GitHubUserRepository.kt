package com.example.myassignmentgithub.repositories

import com.example.myassignmentgithub.network.GitHubUsersApi
import com.example.myassignmentgithub.network.model.UserShortInfo
import com.example.myassignmentgithub.network.model.asDomainModel
import com.example.myassignmentgithub.repository.UserRepository
import io.reactivex.Single

class GitHubUserRepository(private val api: GitHubUsersApi) : UserRepository {
    override fun searchUser(queryText: String): Single<List<UserShortInfo>> {
        return api.searchUsers(queryText).flatMap { response ->
            if (response.isSuccessful && response.body() != null) {
                Single.just(response.body()?.asDomainModel())
            } else {
                Single.error(Throwable(response.errorBody()?.string()))
            }
        }
    }
}
