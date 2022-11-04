package com.example.myassignmentgithub.network.model

import com.squareup.moshi.Json

data class UserCompleteInfo(

        val id: Long,

        val login: String,

        @Json(name = "avatar_url")
        val avatarUrl: String,

        val name: String?,

        val location: String?,

        @Json(name = "html_url")
        val link: String
)

fun UserCompleteInfo.asDomainModel(): UserCompleteInfo {
    return UserCompleteInfo(
            id = id,
            login = login,
            avatarUrl = avatarUrl,
            name = name ?: "",
            location = location ?: "",
            link = link
    )
}
