package com.jk.practice.movies.utils

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class DateUtilsTest {

    @Test
    fun getMillisFromDate() {
        val stringDate = "2019-11-12"
        assertEquals(
            1573534800000, DateUtils.getMillisFromDate(
                stringDate,
                DateUtils.PATTERN_DATE_FROMAPI
            )
        )

    }

    @Test
    fun convertDateForHuman() {
        val stringDate = "2019-11-12"
        assertEquals(
            "12 de Noviembre de 2019", DateUtils.convertDateForHuman(
                stringDate
            )
        )

    }
}