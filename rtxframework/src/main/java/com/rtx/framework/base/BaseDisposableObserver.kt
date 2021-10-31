package com.rtx.framework.base

import io.reactivex.observers.DisposableSingleObserver
import retrofit2.Response

abstract class BaseDisposableObserver<R>(val baseViewModel: BaseViewModel): DisposableSingleObserver<Response<R>>() {

    override fun onSuccess(response: Response<R>) {
        when(val code = getCode(response)) {
            ResponseCode.SUCCESS -> handleSuccess(response.body())
            else -> handleError(response.body(), response.code())
        }
    }

    private fun getCode(response: Response<R>) = if (response.code() == 200) ResponseCode.SUCCESS
        else ResponseCode.API_ERROR

    override fun onError(e: Throwable) {
        val message = if (useDefaultNetworkErrorMessage) networkErrorMessage else e.message ?: networkErrorMessage
        onNetworkError(message)
    }

    private fun handleSuccess(content: R?) {
        onResponseSuccess(content)
    }

    private fun handleError(content: R?, code: Int) {
        onResponseError(content, code)
    }

    abstract fun onResponseSuccess(response: R?)

    open fun onResponseError(response: R?, code: Int) {
        if (handleErrorInBase) {
            baseViewModel.errorMessageLiveData.postValue(apiErrorMessage)
        }
    }

    open fun onNetworkError(message: String) {
        if (handleNetworkErrorInBase) {
            baseViewModel.errorMessageLiveData.postValue(message)
        }
    }

    open val handleErrorInBase: Boolean = true
    open val handleNetworkErrorInBase: Boolean = true
    open val useDefaultNetworkErrorMessage: Boolean = false
    open var networkErrorMessage: String = ResponseCode.NETWORK_FAIL.message
    open var apiErrorMessage: String = ResponseCode.API_ERROR.message
}