package com.rtx.framework.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import com.nhaarman.mockitokotlin2.mock
import com.rtx.framework.model.UiMessage

@RunWith(MockitoJUnitRunner::class)
class BaseDisposableObserverTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Spy
    lateinit var vm: BaseViewModel

    @Mock
    lateinit var response: DummyResponse

    @InjectMocks
    @Spy
    lateinit var dummyDisposableObserver: DummyDisposableObserver

    @Test
    fun `verify vm`() {
        assertEquals(vm, dummyDisposableObserver.baseViewModel)
    }

    @Test
    fun `verify success response`() {
        val success = Response.success(response)
        dummyDisposableObserver.onSuccess(success)

        verify(dummyDisposableObserver).onResponseSuccess(response)
    }

    @Test
    fun `verify error response`() {
        val observer = mock<Observer<UiMessage>>()
        vm.uiMessageLiveData.observeForever(observer)

        dummyDisposableObserver.apiErrorMessage = ResponseCode.API_ERROR.message
        val test = "test"
        val success = Response.error<DummyResponse>(
            404,
            test
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        dummyDisposableObserver.onSuccess(success)

        verify(dummyDisposableObserver).onResponseError(null, 404)
        verify(observer).onChanged(any())
    }

    @Test
    fun `verify error response not handling in base`() {
        val observer = mock<Observer<UiMessage>>()
        vm.uiMessageLiveData.observeForever(observer)

        dummyDisposableObserver.handleErrorInBase = false
        val test = "test"
        val success = Response.error<DummyResponse>(
            404,
            test
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        dummyDisposableObserver.onSuccess(success)

        verify(dummyDisposableObserver).onResponseError(null, 404)
        verify(observer, never()).onChanged(any())
    }

    @Test
    fun `verify network error response`() {
        val observer = mock<Observer<UiMessage>>()
        vm.uiMessageLiveData.observeForever(observer)

        dummyDisposableObserver.networkErrorMessage = ResponseCode.NETWORK_FAIL.message
        val test = "test"
        val t = mock(Throwable::class.java)
        `when`(t.message)
            .thenReturn(test)
        dummyDisposableObserver.onError(t)

        verify(dummyDisposableObserver).onNetworkError(test)
        verify(observer).onChanged(any())
    }

    @Test
    fun `verify network error response not handling in base`() {
        val observer = mock<Observer<UiMessage>>()
        vm.uiMessageLiveData.observeForever(observer)

        dummyDisposableObserver.handleNetworkErrorInBase = false
        dummyDisposableObserver.networkErrorMessage = ResponseCode.NETWORK_FAIL.message
        val test = "test"
        val t = mock(Throwable::class.java)
        `when`(t.message)
            .thenReturn(test)
        dummyDisposableObserver.onError(t)

        verify(dummyDisposableObserver).onNetworkError(test)
        verify(observer, never()).onChanged(any())
    }

    @Test
    fun `verify network error response with null message`() {
        val t = mock(Throwable::class.java)
        dummyDisposableObserver.onError(t)

        verify(dummyDisposableObserver).onNetworkError(ResponseCode.NETWORK_FAIL.message)
    }

    @Test
    fun `verify network error response with default message`() {
        dummyDisposableObserver.networkErrorMessage = ResponseCode.NETWORK_FAIL.message
        dummyDisposableObserver.useDefaultNetworkErrorMessage = true
        val t = mock(Throwable::class.java)
        dummyDisposableObserver.onError(t)
        verify(dummyDisposableObserver).onNetworkError(ResponseCode.NETWORK_FAIL.message)
    }

    companion object {
        class DummyDisposableObserver(baseViewModel: BaseViewModel): BaseDisposableObserver<DummyResponse>(baseViewModel) {
            override fun onResponseSuccess(response: DummyResponse?) {

            }
        }
        class DummyResponse: BaseResponse()
    }
}