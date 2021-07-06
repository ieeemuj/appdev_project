package com.oddlyspaced.redditclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.oddlyspaced.redditclone.api.RedditClient
import com.oddlyspaced.redditclone.api.model.RedditResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RedditClient.getApiClient().fetchSubredditData("wallpapers").enqueue(object : Callback<RedditResponse> {
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                response.body()?.let {
                    it.data.children.forEach { child ->
                        Log.e("Titles", child.data.title)
                    }
                }
            }

            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}