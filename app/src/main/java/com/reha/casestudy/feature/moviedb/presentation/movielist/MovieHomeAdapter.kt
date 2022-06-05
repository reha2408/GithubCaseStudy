package com.reha.casestudy.feature.moviedb.presentation.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.reha.casestudy.databinding.MovieCategoryRowBinding
import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.rtx.framework.base.BaseListAdapter
import com.rtx.framework.base.BaseViewHolder
import com.rtx.framework.extension.createDiffCallback

class MovieHomeAdapter(itemClickListener: (MovieCategory) -> Unit) : BaseListAdapter<MovieCategory>(
    createDiffCallback { oldItem, newItem -> oldItem.id == newItem.id },
    itemClickListener
) {
    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieCategory> =
        MovieCategoryViewHolder(MovieCategoryRowBinding.inflate(inflater, parent, false))
}

class MovieCategoryViewHolder(private val binding: MovieCategoryRowBinding) :
    BaseViewHolder<MovieCategory>(binding) {

    override fun bind(item: MovieCategory) {
        binding.entity = item
        binding.executePendingBindings()
    }
}
