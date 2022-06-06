package com.reha.casestudy.feature.moviedb.presentation.movielist

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reha.casestudy.databinding.MovieCategoryRowBinding
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.rtx.framework.base.BaseListAdapter
import com.rtx.framework.base.BaseViewHolder
import com.rtx.framework.extension.createDiffCallback

class MovieHomeAdapter(private val itemClickListener: (Movie) -> Unit) : BaseListAdapter<MovieCategory>(
    createDiffCallback { oldItem, newItem -> oldItem.discoverType.id == newItem.discoverType.id }
) {
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieCategory> =
        MovieCategoryViewHolder(MovieCategoryRowBinding.inflate(inflater, parent, false), viewPool, itemClickListener)
}

class MovieCategoryViewHolder(
    private val binding: MovieCategoryRowBinding,
    private val viewPool: RecyclerView.RecycledViewPool,
    private val itemClickListener: (Movie) -> Unit
) : BaseViewHolder<MovieCategory>(binding) {

    override fun bind(item: MovieCategory) {
        binding.entity = item
        binding.movieCategoryList.run {
            setRecycledViewPool(viewPool)
            if (adapter == null) {
                addItemDecoration(SpacingItemDecoration(item))
                val movieCategoryAdapter = MovieCategoryAdapter {
                    itemClickListener.invoke(it)
                }
                movieCategoryAdapter.submitList(item.movieList)
                adapter = movieCategoryAdapter
            }
        }
        binding.executePendingBindings()
    }
}

class SpacingItemDecoration(private val item: MovieCategory): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = 20
        if(parent.getChildAdapterPosition(view) == item.movieList.lastIndex) {
            outRect.right = 20
        } else {
            outRect.right = 0
        }
    }
}
