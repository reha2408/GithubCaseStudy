package com.reha.casestudy.feature.moviedb.presentation.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reha.casestudy.databinding.MovieCategoryRowBinding
import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.rtx.framework.base.BaseListAdapter
import com.rtx.framework.base.BaseViewHolder
import com.rtx.framework.extension.createDiffCallback

class MovieHomeAdapter(itemClickListener: (MovieCategory) -> Unit) : BaseListAdapter<MovieCategory>(
    createDiffCallback { oldItem, newItem -> oldItem.id == newItem.id },
    itemClickListener
) {
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieCategory> =
        MovieCategoryViewHolder(MovieCategoryRowBinding.inflate(inflater, parent, false), viewPool)


}

class MovieCategoryViewHolder(
    private val binding: MovieCategoryRowBinding,
    private val viewPool: RecyclerView.RecycledViewPool
) : BaseViewHolder<MovieCategory>(binding) {

    override fun bind(item: MovieCategory) {
        binding.entity = item
        binding.movieCategoryList.setRecycledViewPool(viewPool)
        binding.executePendingBindings()
    }
}
