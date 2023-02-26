package com.rtx.framework.base

import com.rtx.framework.model.UiMessage
import retrofit2.Response
import java.net.HttpURLConnection

abstract class FlowObserver<R>(val baseViewModel: BaseViewModel) {

    open var handleErrorInBase: Boolean = true
    open var handleNetworkErrorInBase: Boolean = true
    open var useDefaultNetworkErrorMessage: Boolean = false
    open var networkErrorMessage: String = ResponseCode.NETWORK_FAIL.message
    open var apiErrorMessage: String = ResponseCode.API_ERROR.message

    fun onSuccess(response: Response<R>) {
        baseViewModel.progressLiveData.postValue(ResponseSubscriptionStatus.FINISHED)
        when (getCode(response)) {
            ResponseCode.SUCCESS -> handleSuccess(response.body())
            else -> handleError(response.body(), response.code())
        }
    }

    private fun getCode(response: Response<R>) = if (response.code() == HttpURLConnection.HTTP_OK) ResponseCode.SUCCESS
    else ResponseCode.API_ERROR

    fun onError(e: Throwable) {
        baseViewModel.progressLiveData.postValue(ResponseSubscriptionStatus.FINISHED)
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
            baseViewModel.uiMessageLiveData.postValue(UiMessage(apiErrorMessage))
        }
    }

    open fun onNetworkError(message: String) {
        if (handleNetworkErrorInBase) {
            baseViewModel.uiMessageLiveData.postValue(UiMessage(message))
        }
    }
}
