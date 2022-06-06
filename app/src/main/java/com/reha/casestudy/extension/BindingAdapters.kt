package com.reha.casestudy.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.reha.casestudy.R

@BindingAdapter("app:showStarIfFavorite")
fun ImageView.showStarIfFavorite(isFavorite: Boolean) {
    val resId = if (isFavorite) R.drawable.ic_star_24 else R.drawable.ic_star_empty_24
    setImageResource(resId)
}

