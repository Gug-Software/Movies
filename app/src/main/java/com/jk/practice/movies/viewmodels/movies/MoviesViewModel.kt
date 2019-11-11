package com.jk.practice.movies.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jk.practice.movies.R
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.utils.Result
import com.jk.practice.movies.utils.WorkStatus
import com.jk.practice.movies.viewmodels.BaseViewModelCoroutine
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: IContractMovies.Model
) : BaseViewModelCoroutine(), IContractMovies.ViewModel {

    private val _movies =
        MutableLiveData<List<Movie>>().apply { value = emptyList() }
    val movies: LiveData<List<Movie>> = _movies

    private val _status = MutableLiveData<WorkStatus>()
    val status: LiveData<WorkStatus> = _status

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    private val _navToDetailMovie = MutableLiveData<Movie>()
    val navToDetailMovie: LiveData<Movie> = _navToDetailMovie

    override fun loadMovies() {

        _status.value = WorkStatus.LOADING

        uiScope.launch {
            try {
                val moviesFromRepository = repository.getTrendingMovies()
                if (moviesFromRepository is Result.Success) {
                    _movies.value = moviesFromRepository.data
                    _status.value = WorkStatus.DONE
                    if (_movies.value.isNullOrEmpty()) {
                        _snackbarText.value = R.string.msg_empty
                    }
                } else {
                    _status.value = WorkStatus.ERROR
                    _movies.value = emptyList()
                    _snackbarText.value = R.string.msg_error
                }
            } catch (e: Exception) {
                _status.value = WorkStatus.ERROR
                _movies.value = emptyList()
                _snackbarText.value = R.string.msg_error
            }
        }

    }

    override fun showMovieDetail(movie: Movie) {
        _navToDetailMovie.value = movie
    }

    fun onNavigateMovieDetailDone() {
        _navToDetailMovie.value = null
    }
}