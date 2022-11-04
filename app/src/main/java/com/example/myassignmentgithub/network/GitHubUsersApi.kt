package com.example.myassignmentgithub.network

import com.example.myassignmentgithub.model.ModelSearch
import com.example.myassignmentgithub.model.UserShortInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val GITHUB_BASE_URL = "https://api.github.com/"
const val GITHUB_USER_INITIAL_KEY = 0L
const val GITHUB_USER_PAGE_SIZE = 30

interface GitHubUsersApi {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String?): Call<ModelSearch>

    @GET("users")
    fun getAllUsers(): Call<List<UserShortInfo>>
}
