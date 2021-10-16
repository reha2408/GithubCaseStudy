package com.rtx.framework.base

import io.reactivex.observers.DisposableSingleObserver
import retrofit2.Response

abstract class BaseDisposableObserver<R>(val baseViewModel: BaseViewModel): DisposableSingleObserver<Response<R>>() {

    override fun onSuccess(response: Response<R>) {
        when(val code = getCode(response)) {
            ResponseCode.SUCCESS -> handleSuccess(response.body())
            else -> handleError(code.message)
        }
    }

    private fun getCode(response: Response<R>) = if (response.code() == 200) ResponseCode.SUCCESS
        else if (response.code() == 404) ResponseCode.NOT_FOUND_ERROR
        else ResponseCode.API_ERROR

    override fun onError(e: Throwable) {
        handleError(ResponseCode.NETWORK_FAIL.message)
    }

    private fun handleSuccess(content: R?) {
        onResponseSuccess(content)
    }

    private fun handleError(errorMessage: String) {
        onResponseError(errorMessage)
    }

    abstract fun onResponseSuccess(response: R?)

    abstract fun onResponseError(errorMessage: String)

}