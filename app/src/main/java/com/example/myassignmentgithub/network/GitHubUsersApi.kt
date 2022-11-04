package com.example.myassignmentgithub.network

import com.example.myassignmentgithub.network.model.UserShortInfo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val GITHUB_BASE_URL = "https://api.github.com/"
const val GITHUB_USER_INITIAL_KEY = 0L
const val GITHUB_USER_PAGE_SIZE = 30

interface GitHubUsersApi {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Single<Response<List<UserShortInfo>>>
}
