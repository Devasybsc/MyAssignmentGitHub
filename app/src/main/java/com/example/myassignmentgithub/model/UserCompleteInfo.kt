package com.example.myassignmentgithub.model

data class UserCompleteInfo(
        val id: Long,
        val login: String,
        val avatarUrl: String,
        val name: String?,
        val location: String?,
        val htmlUrl: String
)

fun UserCompleteInfo.asDomainModel(): UserCompleteInfo {
    return UserCompleteInfo(
            id = id,
            login = login,
            avatarUrl = avatarUrl,
            name = name ?: "",
            location = location ?: "",
            htmlUrl = htmlUrl
    )
}
