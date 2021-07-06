package com.oddlyspaced.redditclone.api

import com.oddlyspaced.redditclone.api.model.RedditResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Interface for end points
interface RedditApi {
    @GET("{subreddit}.json")
    fun fetchSubredditData(
        @Path("subreddit") subredditName: String
    ): Call<RedditResponse>
}