package com.rtx.framework.extension

import androidx.recyclerview.widget.DiffUtil

inline fun <T> createDiffCallback(crossinline itemCheck: (oldItem: T, newItem: T) -> Boolean) =
    createDiffCallback(itemCheck, itemCheck)

inline fun <T> createDiffCallback(
    crossinline itemCheck: (oldItem: T, newItem: T) -> Boolean,
    crossinline contentCheck: (oldItem: T, newItem: T) -> Boolean
) = object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        itemCheck(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        contentCheck(oldItem, newItem)
}
