package com.rtx.framework.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.rtx.framework.R
import com.rtx.framework.util.getOrAwaitValue
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
    fun `handle UiMessage with resource Id`() {
        val id = R.string.test_text

        vm.handleUiMessage(id)

        assertThat(vm.uiMessageLiveData.getOrAwaitValue().messageId).isEqualTo(id)
    }

    @Test
    fun `handle UiMessage with string`() {
        val test = "test"

        vm.handleUiMessage(test)

        assertThat(vm.uiMessageLiveData.getOrAwaitValue().messageText).isEqualTo(test)
    }

    @Test
    fun `add Disposable`() {
        val mock = mock<Disposable>()

        vm.addDisposable(mock)

        assertEquals(vm.compositeDisposable.size(), 1)
    }
}
