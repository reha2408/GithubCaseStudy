package com.rtx.framework.base

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.rtx.framework.dummy.DummyDisposableObserver
import com.rtx.framework.dummy.DummyParams
import com.rtx.framework.dummy.DummyRequestUseCase
import org.junit.Test

class RequestUseCaseTest : BaseUnitTest() {

    @Test
    fun verifyExecute() {
        val baseViewModel = spy<BaseViewModel>()
        val dummyDisposableObserver = spy(DummyDisposableObserver(baseViewModel))
        val requestUseCase = spy(DummyRequestUseCase())
        requestUseCase.execute(dummyDisposableObserver, DummyParams())

        verify(dummyDisposableObserver).onSuccess(any())
    }
}
