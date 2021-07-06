package com.oddlyspaced.redditclone.api

import com.google.gson.annotations.SerializedName

data class RedditChild(
    @SerializedName("data")
    val data: RedditChildData
)