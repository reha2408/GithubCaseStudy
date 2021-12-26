package com.reha.casestudy.feature.github.presentation.repolist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.reha.casestudy.databinding.RepoRowBinding
import com.reha.casestudy.feature.github.data.model.Repo
import com.rtx.framework.base.BaseListAdapter
import com.rtx.framework.base.BaseViewHolder
import com.rtx.framework.extension.createDiffCallback

class RepoAdapter(itemClickListener: (Repo) -> Unit) : BaseListAdapter<Repo>(
    createDiffCallback { oldItem, newItem -> oldItem.id == newItem.id },
    itemClickListener
) {
    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Repo> =
        RepoViewHolder(RepoRowBinding.inflate(inflater, parent, false))
}

class RepoViewHolder(private val binding: RepoRowBinding) :
    BaseViewHolder<Repo>(binding) {

    override fun bind(item: Repo) {
        binding.entity = item
        binding.executePendingBindings()
    }
}
