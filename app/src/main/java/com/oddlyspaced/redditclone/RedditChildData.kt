package com.oddlyspaced.redditclone

import com.google.gson.annotations.SerializedName

data class RedditChildData(
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("ups")
    val upvotes: Int,
)