package com.reha.casestudy.extension

import android.graphics.Rect
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.reha.casestudy.R
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.reha.casestudy.feature.moviedb.presentation.movielist.MovieCategoryAdapter

@BindingAdapter("app:showStarIfFavorite")
fun ImageView.showStarIfFavorite(isFavorite: Boolean) {
    val resId = if (isFavorite) R.drawable.ic_star_24 else R.drawable.ic_star_empty_24
    setImageResource(resId)
}


@BindingAdapter(value = ["setMovieList"])
fun RecyclerView.setMovieList(movieList: List<Movie>?) {
    if (movieList != null) {
        addItemDecoration(object:RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 20
                if(parent.getChildAdapterPosition(view) == movieList.lastIndex) {
                    outRect.right = 20
                } else {
                    outRect.right = 0
                }
            }
        })
        val bookAdapter = MovieCategoryAdapter({})
        bookAdapter.submitList(movieList)

        adapter = bookAdapter
    }
}
