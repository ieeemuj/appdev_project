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

    // Binding variable for layout
    private lateinit var binding: ActivityMainBinding
    // Reddit Api Client
    private val client = RedditClient.getApiClient()

    // Data for the recycler view list
    private val data = arrayListOf<RedditChildData>()
    // Adapter for recycler view
    private val adapter = RedditAdapter(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting up binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initListener()
    }

    /**
     * This method initialises the views
     */
    private fun init() {
        // Setting layout manager and adapter variable to Recycler View
        binding.rvReddit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainActivity.adapter
        }
    }

    /**
     * Initialises the listener
     */
    private fun initListener() {
        // Set click listener for button
        binding.btnSubreddit.setOnClickListener {
            // get currently entered text
            val text = binding.etSubredditNameInput.text.toString()
            // checking if text is not empty
            if (text.isNotEmpty()) {
                loadData(text)
            }
        }
    }

    /**
     * @param subreddit String name of subreddit
     */
    private fun loadData(subreddit: String) {
        // initially hide list and show progress bar
        binding.pbLoading.isVisible = true
        binding.rvReddit.isVisible = false

        client.fetchSubredditData(subreddit).enqueue(object: Callback<RedditResponse> {
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                // if response successful
                if (response.isSuccessful) {
                    // Add data to adapter
                    response.body()?.let {
                        // clear previous data
                        data.clear()
                        // add data
                        it.data.children.forEach { child ->
                            data.add(child.data)
                        }
                        // notify adapter of data change
                        adapter.notifyDataSetChanged()
                        // show recycler view and hide progress bar
                        binding.pbLoading.isVisible = false
                        binding.rvReddit.isVisible = true
                    }
                }
            }

            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                t.printStackTrace()
                // hide list and progress bar on error
                binding.pbLoading.isVisible = false
                binding.rvReddit.isVisible = false
                // toast message for error
                Toast.makeText(applicationContext, getString(R.string.error_occurred), Toast.LENGTH_SHORT).show()
            }

        })
    }
}