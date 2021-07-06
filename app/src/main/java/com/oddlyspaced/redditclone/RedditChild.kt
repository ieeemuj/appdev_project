package com.oddlyspaced.redditclone

import com.google.gson.annotations.SerializedName

data class RedditChild(
    @SerializedName("data")
    val data: RedditChildData
)