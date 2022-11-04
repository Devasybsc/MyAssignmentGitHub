package com.example.myassignmentgithub.model

import com.google.gson.annotations.SerializedName

data class UserShortInfo(
    val id: Long,

    @SerializedName("login")
    var login: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("avatar_url")
    var avatarUrl: String? = null,

    @SerializedName("html_url")
    var htmlUrl: String? = null
)

