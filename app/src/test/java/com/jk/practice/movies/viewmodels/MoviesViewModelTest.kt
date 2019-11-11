package com.jk.practice.movies.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jk.practice.movies.R
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.repository.movies.MoviesRepository
import com.jk.practice.movies.utils.Result
import com.jk.practice.movies.utils.WorkStatus
import com.jk.practice.movies.utilstests.*
import com.jk.practice.movies.viewmodels.movies.MoviesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MoviesViewModelTest {

    @MockK
    lateinit var moviesRepository: MoviesRepository

    private lateinit var moviesViewModel: MoviesViewModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainTestCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun loadMoviesFromRepository_loadingTogglesAndDataLoaded() {

        val listMovies = listOf(
            DomainTestUtils.movieForTest(1),
            DomainTestUtils.movieForTest(2)
        )
        coEvery { moviesRepository.getTrendingMovies() } returns Result.Success(listMovies)

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        moviesViewModel.loadMovies()

        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.status))
            .isEqualTo(WorkStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.status))
            .isEqualTo(WorkStatus.DONE)

        // And data correctly loaded
        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.movies)).hasSize(2)
    }

    @Test
    fun loadMoviesFromRepository_emptyTogglesAndEmptyShow() {

        val listMovies = emptyList<Movie>()
        coEvery { moviesRepository.getTrendingMovies() } returns Result.Success(listMovies)

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        moviesViewModel.loadMovies()

        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.status))
            .isEqualTo(WorkStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.status))
            .isEqualTo(WorkStatus.DONE)

        // And data correctly loaded
        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.movies)).hasSize(0)
        assertSnackbarMessage(moviesViewModel.snackbarMessage, R.string.msg_empty)

    }


    @Test
    fun loadMoviesFromRepository_withError() {

        coEvery { moviesRepository.getTrendingMovies() } returns Result.Error(Exception("Illegal state"))

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        moviesViewModel.loadMovies()

        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.status))
            .isEqualTo(WorkStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.status))
            .isEqualTo(WorkStatus.ERROR)

        // And data correctly loaded
        Truth.assertThat(LiveDataTestUtil.getValue(moviesViewModel.movies)).hasSize(0)
        assertSnackbarMessage(moviesViewModel.snackbarMessage, R.string.msg_error)
    }

    @Test
    fun clickOnMovie_setMovieDetailNavigate() {

        // When opening a new Movie
        val movie = DomainTestUtils.movieForTest(1)
        moviesViewModel.showMovieDetail(movie)

        // Then the event is triggered
        assertLiveDataMovieTriggered(moviesViewModel.navToDetailMovie, movie.id)
    }


}