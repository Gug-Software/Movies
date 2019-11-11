package com.jk.practice.movies.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jk.practice.movies.R
import com.jk.practice.movies.repository.moviedetail.MovieDetailRepository
import com.jk.practice.movies.utils.Result
import com.jk.practice.movies.utils.WorkStatus
import com.jk.practice.movies.utilstests.DomainTestUtils
import com.jk.practice.movies.utilstests.LiveDataTestUtil
import com.jk.practice.movies.utilstests.MainTestCoroutineRule
import com.jk.practice.movies.utilstests.assertSnackbarMessage
import com.jk.practice.movies.viewmodels.moviedetail.MovieDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    private lateinit var viewmodel: MovieDetailViewModel

    @MockK
    private lateinit var repository: MovieDetailRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainTestCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    /**
     * Se repite muchom ver koin
     */
    @ExperimentalCoroutinesApi
    @Before
    fun createMoviesRepository() {

        MockKAnnotations.init(this)
        viewmodel = MovieDetailViewModel(repository)

    }

    @Test
    fun loadMovieDetailFromRepository_loadingTogglesAndDataLoaded() {

        val movieDetail = DomainTestUtils.movieDetailForTest(1)

        coEvery { repository.getMovieDetailById(1) } returns Result.Success(movieDetail)

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        viewmodel.loadMovieDetail(movieDetail.id)

        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.status)).isEqualTo(WorkStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.status)).isEqualTo(WorkStatus.DONE)

        // And data correctly loaded
        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.movie)).isNotNull()
        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.movie).genres).isNotNull()
        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.movie).companies).isNotNull()
        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.movie).languages).isNotNull()
    }

    @Test
    fun loadMovieDetail_errorRepository() {

        val resultMovie = Result.Error(Exception("illegal state"))

        coEvery { repository.getMovieDetailById(1) } returns resultMovie

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        viewmodel.loadMovieDetail(1)

        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.status)).isEqualTo(WorkStatus.LOADING)

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        Truth.assertThat(LiveDataTestUtil.getValue(viewmodel.status)).isEqualTo(WorkStatus.ERROR)
        assertSnackbarMessage(viewmodel.snackbarMessage, R.string.msg_error)

    }
}