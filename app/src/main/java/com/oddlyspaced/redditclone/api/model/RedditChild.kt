package com.oddlyspaced.redditclone.api.model

import com.google.gson.annotations.SerializedName

data class RedditChild(
    @SerializedName("data")
    val data: RedditChildData
)