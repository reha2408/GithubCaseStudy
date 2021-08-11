package com.reha.casestudy.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders

inline val <reified T> T.TAG: String
    get() = T::class.java.simpleName

inline fun <reified T : ViewModel> Fragment.viewModel(factory: Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun <T> LifecycleOwner?.observeLiveData(liveData: LiveData<T>, func: (T) -> (Unit)) = this?.run {
    liveData.observe(this, { liveData.value?.let { func.invoke(it) } })
}