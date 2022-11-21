package com.myu.myumovie.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.myu.myumovie.databinding.FragmentFeedMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedMoviesFragment : Fragment() {
    private val TAG = "FeedMoviesFragment"

    private var _binding : FragmentFeedMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FeedMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.discoverMovieResponse.observe(viewLifecycleOwner) {
           it.results.forEach {
               Log.d(TAG, "setupObserversMovies: " + it.overview)
           }
        }
        viewModel.discoverTvShowsResponse.observe(viewLifecycleOwner) {
            it.results.forEach {
                Log.d(TAG, "setupObserversTvShows: " + it.overview)
            }
        }
    }
}