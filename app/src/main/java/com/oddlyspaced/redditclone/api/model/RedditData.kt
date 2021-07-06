package com.oddlyspaced.redditclone.api.model

import com.google.gson.annotations.SerializedName

data class RedditData(
    @SerializedName("children")
    val children: List<RedditChild>
)