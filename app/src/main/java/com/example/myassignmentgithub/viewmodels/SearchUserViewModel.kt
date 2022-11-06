package com.example.myassignmentgithub.viewmodels

import com.example.myassignmentgithub.R
import com.example.myassignmentgithub.databinding.BindableItemViewModel
import com.example.myassignmentgithub.model.UserShortInfo

class SearchUserViewModel(val userShortInfo: UserShortInfo) : BindableItemViewModel {
    override val layoutId: Int = R.layout.list_item_user
    override val viewType: Int = 1
}