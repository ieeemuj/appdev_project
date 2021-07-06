package com.oddlyspaced.redditclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oddlyspaced.redditclone.api.model.RedditChildData
import com.oddlyspaced.redditclone.databinding.ItemPostBinding

class RedditAdapter(private val data: ArrayList<RedditChildData>) : RecyclerView.Adapter<RedditAdapter.RedditViewHolder>() {

    inner class RedditViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RedditChildData) {
            binding.txTitle.text = item.title
            binding.txAuthor.text = item.author
            binding.txUpvotes.text = item.upvotes.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        return RedditViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}