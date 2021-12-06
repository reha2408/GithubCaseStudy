package com.rtx.framework.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.rtx.framework.R
import com.rtx.framework.model.UiMessage
import io.reactivex.disposables.Disposable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Spy
    lateinit var vm: BaseViewModel

    @Test
    fun `verify onCleared`() {
        val mock = mock<Disposable>()
        vm.compositeDisposable.add(mock)

        vm.onCleared()

        assertEquals(vm.compositeDisposable.size(), 0)
    }

    @Test
    fun `handle UiMessage string`() {
        val observer = mock<Observer<UiMessage>>()
        vm.uiMessageLiveData.observeForever(observer)

        vm.handleUiMessage(R.string.test_text)

        verify(observer).onChanged(any())
    }

    @Test
    fun `handle UiMessage resource Id`() {
        val observer = mock<Observer<UiMessage>>()
        vm.uiMessageLiveData.observeForever(observer)
        val test = "test"

        vm.handleUiMessage(test)

        verify(observer).onChanged(any())
    }

    @Test
    fun `add Disposable`() {
        val mock = mock<Disposable>()

        vm.addDisposable(mock)

        assertEquals(vm.compositeDisposable.size(), 1)
    }
}
