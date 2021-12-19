package com.rtx.framework.base

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.rtx.framework.dummy.DummyDisposableObserver
import com.rtx.framework.dummy.DummyFailingRequestUseCase
import com.rtx.framework.dummy.DummyParams
import com.rtx.framework.dummy.DummyRequestUseCase
import com.rtx.framework.util.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Test

class RequestUseCaseTest : BaseUnitTest() {

    @Test
    fun `verify with success single`() {
        // given
        val baseViewModel = spy<BaseViewModel>()
        val dummyDisposableObserver = spy(DummyDisposableObserver(baseViewModel))
        val requestUseCase = spy(DummyRequestUseCase())

        // when
        requestUseCase.execute(dummyDisposableObserver, DummyParams())

        // then
        verify(dummyDisposableObserver).onSuccess(any())
        assertEquals(baseViewModel.progressLiveData.getOrAwaitValue(), ResponseSubscriptionStatus.FINISHED)
    }

    @Test
    fun `verify with never single`() {
        // given
        val baseViewModel = spy<BaseViewModel>()
        val dummyDisposableObserver = spy(DummyDisposableObserver(baseViewModel))
        val requestUseCase = spy(DummyFailingRequestUseCase())

        // when
        requestUseCase.execute(dummyDisposableObserver, DummyParams())

        // then
        assertEquals(baseViewModel.progressLiveData.getOrAwaitValue(), ResponseSubscriptionStatus.SUBSCRIBED)
    }
}
