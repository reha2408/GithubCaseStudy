package com.rtx.framework.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Base adapter to reduce boilerplate on creating / binding view holders.
 */
abstract class BaseListAdapter<T>(
    callback: DiffUtil.ItemCallback<T>,
    var clickListener: ((T) -> Unit)? = null
) : ListAdapter<T, BaseViewHolder<T>>(callback) {

    abstract fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        createViewHolder(
            LayoutInflater.from(parent.context),
            parent,
            viewType
        )

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        bindItem(holder, currentList[position])

    fun bindItem(holder: BaseViewHolder<T>, item: T) {
        clickListener?.let {
            holder.itemView.setOnClickListener { _ ->
                it.invoke(item)
            }
        }
        holder.bind(item)
    }

    override fun getItemCount() = currentList.size
}

abstract class BaseViewHolder<T>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}
