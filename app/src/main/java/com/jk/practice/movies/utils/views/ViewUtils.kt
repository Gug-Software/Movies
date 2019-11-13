package com.jk.practice.movies.utils.views

import android.view.View

fun visibleViews(vararg view: View) {
    view.forEach {
        it.visibility = View.VISIBLE
    }
}

fun goneViews(vararg view: View) {
    view.forEach {
        it.visibility = View.GONE
    }
}