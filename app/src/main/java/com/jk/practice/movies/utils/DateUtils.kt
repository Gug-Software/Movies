package com.jk.practice.movies.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*


class DateUtils {

    companion object {

        val PATTERN_DATE_FROMAPI = "yyyy-MM-dd"
        val PATTERN_DATE_FORHUMAN = "dd de MM de yyyy"

        fun getMillisFromDate(
            dateString: String,
            datePattern: String
        ): Long {

            val sdf = SimpleDateFormat(datePattern)
            try {
                val mDate = sdf.parse(dateString)
                val timeInMilliseconds = mDate.getTime()
                println("Date in milli :: $timeInMilliseconds")
                return timeInMilliseconds
            } catch (e: ParseException) {
                return 0
            }

        }

        fun convertDateForHuman(
            dateString: String
        ): String {

            val cal = Calendar.getInstance()
            cal.time = Date(getMillisFromDate(dateString, PATTERN_DATE_FROMAPI))

            val mes = getMesFromMonthNumber(cal.get(Calendar.MONTH))

            return "${cal.get(Calendar.DAY_OF_MONTH)} de ${mes} de ${cal.get(
                Calendar.YEAR
            )}"
        }

        private fun getMesFromMonthNumber(get: Int): Any {

            return when (get) {
                Calendar.JANUARY -> "Enero"
                Calendar.FEBRUARY -> "Febrero"
                Calendar.MARCH -> "Marzo"
                Calendar.APRIL -> "Abril"
                Calendar.MAY -> "Mayo"
                Calendar.JUNE -> "Junio"
                Calendar.JULY -> "Julio"
                Calendar.AUGUST -> "Agosto"
                Calendar.SEPTEMBER -> "Septiembre"
                Calendar.OCTOBER -> "Octubre"
                Calendar.NOVEMBER -> "Noviembre"
                Calendar.DECEMBER -> "Diciembre"
                else -> ""
            }


        }

    }


}