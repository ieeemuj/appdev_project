package com.oddlyspaced.redditclone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.oddlyspaced.redditclone.R
import com.oddlyspaced.redditclone.adapter.RedditAdapter
import com.oddlyspaced.redditclone.api.RedditClient
import com.oddlyspaced.redditclone.api.model.RedditChildData
import com.oddlyspaced.redditclone.api.model.RedditResponse
import com.oddlyspaced.redditclone.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val client = RedditClient.getApiClient()

    private val data = arrayListOf<RedditChildData>()
    private val adapter = RedditAdapter(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initListener()
    }

    private fun init() {
        binding.rvReddit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainActivity.adapter
        }
    }

    private fun initListener() {
        binding.btnSubreddit.setOnClickListener {
            val text = binding.etSubredditNameInput.text.toString()
            if (text.isNotEmpty()) {
                loadData(text)
            }
        }
    }

    private fun loadData(subreddit: String) {
        binding.pbLoading.isVisible = true
        binding.rvReddit.isVisible = false
        client.fetchSubredditData(subreddit).enqueue(object: Callback<RedditResponse> {
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data.clear()
                        it.data.children.forEach { child ->
                            data.add(child.data)
                        }
                        adapter.notifyDataSetChanged()
                        binding.pbLoading.isVisible = false
                        binding.rvReddit.isVisible = true
                    }
                }
            }

            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                t.printStackTrace()
                binding.pbLoading.isVisible = false
                binding.rvReddit.isVisible = false
                Toast.makeText(applicationContext, getString(R.string.error_occurred), Toast.LENGTH_SHORT).show()
            }

        })
    }
}