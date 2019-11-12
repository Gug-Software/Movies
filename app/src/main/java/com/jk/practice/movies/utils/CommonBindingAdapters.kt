package com.jk.practice.movies.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}

@BindingAdapter("showFromApiStatus")
fun showFromApiStatus(progressBar: ProgressBar, status: WorkStatus?) {
    when (status) {
        WorkStatus.LOADING -> progressBar.visibility = View.VISIBLE
        else -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("hideFromApiStatusError")
fun hideFromApiStatusError(view: View, status: WorkStatus?) {
    when (status) {
        WorkStatus.LOADING, WorkStatus.DONE -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("hideFromApiStatus")
fun hideFromApiStatus(view: View, status: WorkStatus?) {
    when (status) {
        WorkStatus.LOADING, WorkStatus.ERROR -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}