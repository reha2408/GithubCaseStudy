package com.rtx.framework.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class FlowRequestUseCase<R, Params> {

    abstract suspend fun buildFlowObservable(params: Params): Flow<Response<R>>

    fun execute(observer: FlowObserver<R>, params: Params) {
        val progressLiveData = observer.baseViewModel.progressLiveData
        progressLiveData.postValue(ResponseSubscriptionStatus.SUBSCRIBED)

        observer.baseViewModel.viewModelScope.launch {
            buildFlowObservable(params)
                .flowOn(Dispatchers.IO)
                .catch {
                    observer.onError(it)
                }
                .collect {
                    observer.onSuccess(it)
                }
        }
    }

    fun executeSilently(observer: FlowObserver<R>, params: Params) {
        observer.baseViewModel.viewModelScope.launch {
            buildFlowObservable(params)
                .flowOn(Dispatchers.IO)
                .catch {
                    observer.onError(it)
                }
                .collect {
                    observer.onSuccess(it)
                }
        }
    }
}
