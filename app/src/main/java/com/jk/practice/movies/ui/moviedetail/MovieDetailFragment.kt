package com.jk.practice.movies.ui.moviedetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.jk.practice.movies.R
import com.jk.practice.movies.databinding.FragmentMovieDetailBinding
import com.jk.practice.movies.viewmodels.moviedetail.MovieDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : Fragment() {

    lateinit var binding: FragmentMovieDetailBinding
    // Lazy inject ViewModel
    private val viewModel by viewModel<MovieDetailViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_detail, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //configureRecyclers()
        //defineSkeletonScreens()
        //defineObservers()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val args = MovieDetailFragmentArgs.fromBundle(arguments!!)
        viewModel.loadMovieDetail(args.movieId)
    }

}
