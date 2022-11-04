package com.example.myassignmentgithub.datasource

import com.example.myassignmentgithub.model.UserShortInfo

interface RemoteUserDatasource {
    fun searchUser(queryText: String): List<UserShortInfo>
    fun getAllUsers(): List<UserShortInfo>
}
