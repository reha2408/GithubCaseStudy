package com.reha.casestudy.feature.moviedb.presentation.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.reha.casestudy.databinding.MovieRowBinding
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.rtx.framework.base.BaseListAdapter
import com.rtx.framework.base.BaseViewHolder
import com.rtx.framework.extension.createDiffCallback

class MovieCategoryAdapter(itemClickListener: (Movie) -> Unit) : BaseListAdapter<Movie>(
    createDiffCallback { oldItem, newItem -> oldItem.id == newItem.id },
    itemClickListener
) {
    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie> =
        MovieViewHolder(MovieRowBinding.inflate(inflater, parent, false))
}

class MovieViewHolder(private val binding: MovieRowBinding) :
    BaseViewHolder<Movie>(binding) {

    override fun bind(item: Movie) {
        binding.entity = item
        binding.executePendingBindings()
    }
}
