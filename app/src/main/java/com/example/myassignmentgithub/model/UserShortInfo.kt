package com.example.myassignmentgithub.model

import androidx.lifecycle.MutableLiveData
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
) {
    var isFavorite: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun setFavorite(favorite: Boolean) {
        if(this.isFavorite == null){
            this.isFavorite = MutableLiveData<Boolean>(false)
        }
        this.isFavorite.postValue(favorite)
    }
}
