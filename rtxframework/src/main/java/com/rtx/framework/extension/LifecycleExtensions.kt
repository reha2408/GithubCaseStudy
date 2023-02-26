package com.rtx.framework.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline val <reified T> T.TAG: String
    get() = T::class.java.simpleName

fun <T> LifecycleOwner?.observeLiveData(liveData: LiveData<T>, func: (T) -> (Unit)) = this?.run {
    liveData.observe(this) { liveData.value?.let { func.invoke(it) } }
}
