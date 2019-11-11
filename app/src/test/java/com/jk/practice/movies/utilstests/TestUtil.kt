package com.jk.practice.movies.utilstests

import androidx.lifecycle.LiveData
import com.jk.practice.movies.domain.domain.movies.Movie
import org.junit.Assert.assertEquals

fun assertLiveDataMovieTriggered(
    liveData: LiveData<Movie>,
    taskId: Int
) {
    val value = LiveDataTestUtil.getValue(liveData)
    assertEquals(value.id, taskId)
}

fun assertSnackbarMessage(snackbarLiveData: LiveData<Int>, messageId: Int) {
    val value: Int = LiveDataTestUtil.getValue(snackbarLiveData)
    assertEquals(value, messageId)
}