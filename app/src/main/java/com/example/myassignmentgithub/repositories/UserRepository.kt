package com.example.myassignmentgithub.repositories

import com.example.myassignmentgithub.model.UserShortInfo

interface  UserRepository {
    fun searchUser(query: String): List<UserShortInfo>
    fun getAllUsers(): List<UserShortInfo>
}