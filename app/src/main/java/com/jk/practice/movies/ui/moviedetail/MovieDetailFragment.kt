package com.jk.practice.movies.ui.moviedetail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.jk.practice.movies.databinding.FragmentMovieDetailBinding
import com.jk.practice.movies.ui.moviedetail.adapters.GenresAdapter
import com.jk.practice.movies.ui.moviedetail.adapters.ProductionCompanyAdapter
import com.jk.practice.movies.ui.moviedetail.adapters.SpokenLanguageAdapter
import com.jk.practice.movies.utils.WorkStatus.DONE
import com.jk.practice.movies.viewmodels.moviedetail.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import android.view.animation.AnimationUtils
import com.google.android.material.snackbar.Snackbar
import com.jk.practice.movies.R
import com.jk.practice.movies.utils.views.goneViews
import com.jk.practice.movies.utils.views.visibleViews


class MovieDetailFragment : Fragment() {

    lateinit var binding: FragmentMovieDetailBinding
    private val movieDetailViewModel by viewModel<MovieDetailViewModel>()

    lateinit var adapterGenres: GenresAdapter
    lateinit var adapterLanguages: SpokenLanguageAdapter
    lateinit var adapterCompanies: ProductionCompanyAdapter

    private val animationListener = object : Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            animation?.let {
                when (it) {
                    animationForDescription -> {
                        movieDetail_constraintLanguageAndBudge.startAnimation(
                            animationForQualifyAndBudge
                        )
                    }
                    animationForQualifyAndBudge -> {
                        movieDetail_constraintGenres.startAnimation(animationForRecyclersLeft)
                        movieDetail_constraintLanguages.startAnimation(animationForRecyclersRight)
                        movieDetail_constraintCompanies.startAnimation(animationForRecyclersLeft)
                    }
                }
            }

        }

        override fun onAnimationStart(animation: Animation?) {

            animation?.let {
                when (it) {
                    animationForDescription -> {
                        goneViews(
                            movieDetail_constraintLanguageAndBudge, movieDetail_constraintGenres,
                            movieDetail_constraintLanguages, movieDetail_constraintCompanies
                        )
                    }
                    animationForQualifyAndBudge -> {
                        visibleViews(movieDetail_constraintLanguageAndBudge)
                    }
                    animationForRecyclersLeft -> {
                        visibleViews(movieDetail_constraintGenres, movieDetail_constraintCompanies)
                    }
                    animationForRecyclersRight -> {
                        visibleViews(movieDetail_constraintLanguages)
                    }
                }
            }

        }
    }

    val animationForDescription: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.slide_in_top).apply {
            setAnimationListener(animationListener)
        }
    }

    val animationForQualifyAndBudge: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.slide_in_top).apply {
            setAnimationListener(animationListener)
        }
    }

    val animationForRecyclersLeft: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.slide_in_left).apply {
            setAnimationListener(animationListener)
        }
    }

    val animationForRecyclersRight: Animation by lazy {
        AnimationUtils.loadAnimation(context, R.anim.slide_in_right).apply {
            setAnimationListener(animationListener)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(
            inflater, R.layout.fragment_movie_detail, container, false
        ).apply {
            viewModel = movieDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            movieDetailToolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
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
    }

    private fun defineObservers() {

        movieDetailViewModel.movie.observe(this, Observer {
            adapterGenres.submitList(it.genres)
            adapterLanguages.submitList(it.languages)
            adapterCompanies.submitList(it.companies)
        })

        movieDetailViewModel.status.observe(this, Observer {
            when (it) {
                DONE -> {
                    movieDetail_textViewDescription.startAnimation(animationForDescription)
                    movieDetail_textViewDescriptionValue.startAnimation(animationForDescription)
                }

            }
        })

        movieDetailViewModel.snackbarMessage.observe(this, Observer {
            Snackbar.make(binding.movieDetailCoordinator, getString(it), Snackbar.LENGTH_LONG)
                .show()
            movieDetailViewModel.onSnackBarDone()
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
