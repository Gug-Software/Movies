package com.jk.practice.movies.ui.movies


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.jk.practice.movies.R
import com.jk.practice.movies.databinding.FragmentMoviesBinding
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.ui.movies.adapter.MovieItemListener
import com.jk.practice.movies.ui.movies.adapter.MoviesAdapter
import com.jk.practice.movies.viewmodels.movies.MoviesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment
    : Fragment(), IContractMovies.View {

    lateinit var binding: FragmentMoviesBinding
    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies, container, false
        )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        configureRecyclerMovies()
        defineObservers()

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMovies()
    }

    private fun configureRecyclerMovies() {

        val adapter = MoviesAdapter(
            MovieItemListener(
                clickListener = {
                    //viewModel.showMovieDetail(it)
                    Toast.makeText(context, "click movie", Toast.LENGTH_SHORT).show()
                }
            )
        )

        binding.moviesRecycler.adapter = adapter

        viewModel.movies.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun defineObservers() {
    }


}
