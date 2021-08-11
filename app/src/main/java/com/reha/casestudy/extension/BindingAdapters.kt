package com.reha.casestudy.extension

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.reha.casestudy.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrBlank()) {
        clipToOutline = true
        Picasso.with(context)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("android:visibility")
fun View?.setVisibility(value: Boolean) = this?.run {
    visibility = if (value) VISIBLE else GONE
}

@BindingAdapter("app:showStarIfFavorite")
fun ImageView.showStarIfFavorite(isFavorite: Boolean) {
    val resId = if (isFavorite) R.drawable.ic_star_24 else R.drawable.ic_star_empty_24
    setImageResource(resId)
}