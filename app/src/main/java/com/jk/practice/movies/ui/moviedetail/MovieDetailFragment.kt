package com.jk.practice.movies.ui.moviedetail

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.jk.practice.movies.R
import com.jk.practice.movies.databinding.FragmentMovieDetailBinding
import com.jk.practice.movies.ui.moviedetail.adapters.GenresAdapter
import com.jk.practice.movies.ui.moviedetail.adapters.ProductionCompanyAdapter
import com.jk.practice.movies.ui.moviedetail.adapters.SpokenLanguageAdapter
import com.jk.practice.movies.viewmodels.moviedetail.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    lateinit var binding: FragmentMovieDetailBinding
    private val movieDetailViewModel by viewModel<MovieDetailViewModel>()

    lateinit var adapterGenres: GenresAdapter
    lateinit var adapterLanguages: SpokenLanguageAdapter
    lateinit var adapterCompanies: ProductionCompanyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(
            inflater, R.layout.fragment_movie_detail, container, false
        ).apply {

            viewModel = movieDetailViewModel
            lifecycleOwner = viewLifecycleOwner

        }

        configureRecyclers()
        defineObservers()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }


    override fun onStart() {
        super.onStart()
        val args = MovieDetailFragmentArgs.fromBundle(arguments!!)
        movieDetailViewModel.loadMovieDetail(args.movieId)
        configureFragmentViews()
    }

    private fun configureFragmentViews() {

        binding.movieDetailToolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }


    }

    private fun defineObservers() {

        movieDetailViewModel.movie.observe(this, Observer {
            adapterGenres.submitList(it.genres)
            adapterLanguages.submitList(it.languages)
            adapterCompanies.submitList(it.companies)
        })

    }

    private fun configureRecyclers() {

        adapterGenres = GenresAdapter()
        binding.movieDetailRecyclerGenres.adapter = adapterGenres

        adapterLanguages = SpokenLanguageAdapter()
        binding.movieDetailRecyclerLanguages.adapter = adapterLanguages

        adapterCompanies = ProductionCompanyAdapter()
        binding.movieDetailRecyclerCompanies.adapter = adapterCompanies

    }


}
