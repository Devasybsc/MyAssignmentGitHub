package com.example.myassignmentgithub.repository

import com.example.myassignmentgithub.network.model.UserShortInfo
import io.reactivex.Single

interface UserRepository {

    fun searchUser(queryText: String): Single<List<UserShortInfo>>
}
