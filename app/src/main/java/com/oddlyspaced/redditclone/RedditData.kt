package com.oddlyspaced.redditclone

import com.google.gson.annotations.SerializedName

data class RedditData(
    @SerializedName("children")
    val children: List<RedditChild>
)