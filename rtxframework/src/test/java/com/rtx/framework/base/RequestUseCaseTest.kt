package com.rtx.framework.base

import com.nhaarman.mockitokotlin2.mock
import com.rtx.framework.dummy.DummyDisposableObserver
import com.rtx.framework.dummy.DummyParams
import com.rtx.framework.dummy.DummyResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RequestUseCaseTest {

    @Test
    fun verifyExecute() {
        val dummyDisposableObserver = mock<DummyDisposableObserver>()
        val requestUseCase =
            mock<RequestUseCase<DummyResponse, DummyParams>>()
        requestUseCase.execute(dummyDisposableObserver, DummyParams())
    }
}
