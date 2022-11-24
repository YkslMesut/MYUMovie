package com.myu.myumovie.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.myu.myumovie.adapter.MoviesAdapter
import com.myu.myumovie.adapter.TvShowsAdapter
import com.myu.myumovie.databinding.FragmentFeedMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedMoviesFragment : Fragment() {
    private val TAG = "FeedMoviesFragment"

    private lateinit var tvShowsAdapter: TvShowsAdapter
    private lateinit var moviesAdapter: MoviesAdapter
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

        setupRecyclerView()
        setupObservers()
    }


    private fun setupRecyclerView() {
        tvShowsAdapter = TvShowsAdapter()
        moviesAdapter = MoviesAdapter()

        binding.tvShowsRv.apply {
            adapter = tvShowsAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }

        binding.moviesRv.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

    }

    private fun setupObservers() {
        viewModel.discoverMovieResponse.observe(viewLifecycleOwner) {
          moviesAdapter.movies = it.results
        }
        viewModel.discoverTvShowsResponse.observe(viewLifecycleOwner) {
            tvShowsAdapter.tvShows = it.results
        }
    }
}