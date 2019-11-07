package com.jk.practice.movies.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.repository.movies.MoviesRepository
import com.jk.practice.movies.utils.Result
import com.jk.practice.movies.utils.WorkStatus
import com.jk.practice.movies.viewmodels.BaseViewModelCoroutine
import kotlinx.coroutines.launch
import java.util.ArrayList

class MoviesViewModel(
    private val repository: IContractMovies.Model
) : BaseViewModelCoroutine(), IContractMovies.ViewModel {

    private val _movies =
        MutableLiveData<List<DtoTrendingResponse.DtoMovie>>().apply { value = emptyList() }
    val movies: LiveData<List<DtoTrendingResponse.DtoMovie>> = _movies

    private val _status = MutableLiveData<WorkStatus>()
    val status: LiveData<WorkStatus> = _status

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarMessage: LiveData<Int> = _snackbarText

    override fun loadMovies() {

        _status.value = WorkStatus.LOADING

        uiScope.launch {
            try {
                val moviesFromRepository = repository.getTrendingMovies()
                if (moviesFromRepository is Result.Success) {
                    _movies.value = moviesFromRepository.data
                    _status.value = WorkStatus.DONE
                } else {
                    _status.value = WorkStatus.ERROR
                    _movies.value = emptyList()
                }
            } catch (e: Exception) {
                _status.value = WorkStatus.ERROR
                _movies.value = emptyList()
            }
        }


    }
}