package com.example.myassignmentgithub.ui.listeners

import com.example.myassignmentgithub.adapters.OnItemClickListener
import com.example.myassignmentgithub.model.UserShortInfo

interface SearchUsersListener : OnItemClickListener{
    fun retryClicked()
    fun setFavoriteUser(user: UserShortInfo)
}