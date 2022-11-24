package com.myu.myumovie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myu.myumovie.data.model.DiscoverMovieResult
import com.myu.myumovie.data.model.DiscoverTvShowsResult
import com.myu.myumovie.databinding.RowMoviesBinding
import com.myu.myumovie.databinding.RowTvShowBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding : RowMoviesBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<DiscoverMovieResult>(){
        override fun areItemsTheSame(
            oldItem: DiscoverMovieResult,
            newItem: DiscoverMovieResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DiscoverMovieResult,
            newItem: DiscoverMovieResult
        ): Boolean {
          return oldItem == newItem
        }


    }
    private val differ = AsyncListDiffer(this,diffCallback)
    var movies : List<DiscoverMovieResult>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RowMoviesBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMovies = movies[position]
        holder.binding.name.text = currentMovies.overview

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}