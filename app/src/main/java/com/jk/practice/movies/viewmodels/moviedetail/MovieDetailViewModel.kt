package com.jk.practice.movies.viewmodels.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jk.practice.movies.R
import com.jk.practice.movies.domain.contracts.moviedetail.IContractMovieDetail
import com.jk.practice.movies.domain.domain.moviedetail.MovieDetail
import com.jk.practice.movies.utils.Result
import com.jk.practice.movies.utils.WorkStatus
import com.jk.practice.movies.viewmodels.BaseViewModelCoroutine
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: IContractMovieDetail.Model
) : BaseViewModelCoroutine(), IContractMovieDetail.ViewModel {

    private val _movie = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail> = _movie

    private val _status = MutableLiveData<WorkStatus>()
    val status: LiveData<WorkStatus> = _status

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    override fun loadMovieDetail(movieId: Int) {

        _status.value = WorkStatus.LOADING

        uiScope.launch {
            try {
                val movieDetail = repository.getMovieDetailById(movieId)
                if (movieDetail is Result.Success) {
                    _movie.value = movieDetail.data
                    _status.value = WorkStatus.DONE
                } else {
                    _status.value = WorkStatus.ERROR
                    _snackbarText.value = R.string.app_name
                }
            } catch (e: Exception) {
                _status.value = WorkStatus.ERROR
            }
        }

    }

}