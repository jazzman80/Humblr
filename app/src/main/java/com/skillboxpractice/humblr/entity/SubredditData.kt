package com.skillboxpractice.humblr.entity

import com.google.gson.annotations.SerializedName

data class SubredditData(
    val title: String,
    val id: String,

    @SerializedName("public_description")
    val description: String,

    @SerializedName("header_img")
    val headerImg: String
)
