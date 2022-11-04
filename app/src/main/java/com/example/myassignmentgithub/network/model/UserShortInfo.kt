package com.example.myassignmentgithub.network.model

import com.squareup.moshi.Json

data class UserShortInfo(
    val id: Long,
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "html_url")
    val link: String
)

fun UserShortInfo.asDomainModel(): UserShortInfo {
    return UserShortInfo(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        link = link
    )
}

fun List<UserShortInfo>.asDomainModel(): List<UserShortInfo> {
    return map {
        it.asDomainModel()
    }
}
