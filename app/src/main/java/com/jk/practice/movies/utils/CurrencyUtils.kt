package com.jk.practice.movies.utils

import java.text.NumberFormat
import java.util.*

object CurrencyUtils {

    fun formatCurrency(number: Any?): String {
        val locale = Locale("es", "CO")
        val numberFormat = NumberFormat.getCurrencyInstance(locale).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 0
        }
        return try {
            numberFormat.format(number)
        } catch (exception: Exception) {
            numberFormat.format(0)
        }
    }
}