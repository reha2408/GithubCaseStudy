package com.rtx.framework.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel(){

    val compositeDisposable = CompositeDisposable()

    private val progress = MutableLiveData<ResponseSubscriptionStatus>()
    val progressLiveData : MutableLiveData<ResponseSubscriptionStatus> get() = progress

    private val errorMessage = MutableLiveData<String>()
    val errorMessageLiveData : MutableLiveData<String> get() = errorMessage

    override fun onCleared(){
        super.onCleared()
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun handleError(message: String?) {
        errorMessage.value = message
    }
}

