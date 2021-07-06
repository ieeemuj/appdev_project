package com.oddlyspaced.redditclone

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("data")
    val data: RedditData
)