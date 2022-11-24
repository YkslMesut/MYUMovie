package com.myu.myumovie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.myu.myumovie.data.model.DiscoverTvShowsResult
import com.myu.myumovie.databinding.RowTvShowBinding

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding : RowTvShowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<DiscoverTvShowsResult>(){
        override fun areItemsTheSame(oldItem: DiscoverTvShowsResult, newItem: DiscoverTvShowsResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DiscoverTvShowsResult, newItem: DiscoverTvShowsResult): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var tvShows : List<DiscoverTvShowsResult>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RowTvShowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvShow = tvShows[position]
        holder.binding.name.text = currentTvShow.name
        /* Glide.with(holder.binding.root)
             .load(currentDogImage)
             .transform(CircleCrop())
             .into(holder.binding.dogImage)*/

    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

}