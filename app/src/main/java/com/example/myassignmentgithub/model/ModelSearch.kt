package com.example.myassignmentgithub.model

import com.google.gson.annotations.SerializedName

data class ModelSearch (
    @SerializedName("items")
    var modelSearchData: List<UserShortInfo>? = null
)