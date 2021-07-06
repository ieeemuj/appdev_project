package com.oddlyspaced.redditclone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.oddlyspaced.redditclone.R
import com.oddlyspaced.redditclone.api.RedditClient
import com.oddlyspaced.redditclone.api.model.RedditResponse
import com.oddlyspaced.redditclone.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}