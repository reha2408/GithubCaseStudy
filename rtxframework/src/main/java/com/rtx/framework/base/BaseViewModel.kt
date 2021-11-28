package com.rtx.framework.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rtx.framework.model.UiMessage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val progress = MutableLiveData<ResponseSubscriptionStatus>()
    val progressLiveData: MutableLiveData<ResponseSubscriptionStatus> get() = progress

    private val uiMessage = MutableLiveData<UiMessage>()
    val uiMessageLiveData: MutableLiveData<UiMessage> get() = uiMessage

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun handleUiMessage(message: String?) {
        uiMessage.value = UiMessage(message)
    }

    fun handleUiMessage(strId: Int?) {
        uiMessage.value = UiMessage(messageId = strId)
    }
}
