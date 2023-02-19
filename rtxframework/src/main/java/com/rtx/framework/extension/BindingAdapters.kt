package com.rtx.framework.extension

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrBlank()) {
        clipToOutline = true
        Picasso.with(context)
            .load(url)
            .fit()
            .into(this)
    }
}

@BindingAdapter("android:visibility")
fun View?.setVisibility(value: Boolean) = this?.run {
    visibility = if (value) VISIBLE else GONE
}
