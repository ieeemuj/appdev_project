package com.oddlyspaced.redditclone.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Reddit api client
object RedditClient {
    fun getApiClient(): RedditApi {
        val gson = GsonBuilder().apply {
            setLenient()
        }.create()
        val okHttpClient = OkHttpClient.Builder().apply {
            readTimeout(5000, TimeUnit.SECONDS)
            connectTimeout(100, TimeUnit.SECONDS)
        }.build()
        return Retrofit.Builder().apply {
            baseUrl("https://www.reddit.com/r/").client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build().create(RedditApi::class.java)
    }
}