package com.skillboxpractice.humblr.main.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skillboxpractice.humblr.R
import com.skillboxpractice.humblr.databinding.ItemSubredditBinding
import com.skillboxpractice.humblr.entity.Subreddit

class FeedAdapter(
    private val parentViewModel: ParentViewModel
) : PagingDataAdapter<Subreddit, FeedAdapter.ViewHolder>(
    DiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSubredditBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_subreddit,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.parentViewModel = this.parentViewModel
        val fuckYou = getItem(position)
        holder.binding.model = getItem(position)
    }

    class ViewHolder(binding: ItemSubredditBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemSubredditBinding

        init {
            this.binding = binding
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Subreddit>() {
        override fun areItemsTheSame(oldItem: Subreddit, newItem: Subreddit): Boolean =
            oldItem.data.id == newItem.data.id

        override fun areContentsTheSame(oldItem: Subreddit, newItem: Subreddit): Boolean =
            oldItem == newItem

    }

    interface ParentViewModel {
        val onSubscribeClick: (String, Boolean) -> Unit
    }
}