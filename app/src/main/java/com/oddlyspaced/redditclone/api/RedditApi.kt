package com.oddlyspaced.redditclone.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditApi {
    @GET("{subreddit}.json")
    fun fetchSubredditData(
        @Path("subreddit") subredditName: String
    ): Call<RedditResponse>
}