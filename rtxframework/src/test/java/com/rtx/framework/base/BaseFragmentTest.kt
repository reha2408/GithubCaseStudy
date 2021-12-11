package com.rtx.framework.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.ViewDataBinding
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseFragmentTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var activity: BaseActivity

    @Spy
    lateinit var vm: BaseViewModel

    @Spy
    lateinit var fragment: BaseFragment<BaseViewModel, ViewDataBinding>

    @Test
    fun `verify observeViewModel`() {
        fragment.viewModel = vm

        fragment.observeBaseViewModel()

        verify(vm).progressLiveData
        verify(vm).uiMessageLiveData
    }

    @Test
    fun `verify progress delegates to activity`() {
        `when`(fragment.activity).thenReturn(activity)

        fragment.lockScreen()
        fragment.unlockScreen()

        verify(activity).lockScreen()
        verify(activity).unlockScreen()
    }

    @Test
    fun `verify progress delegates not to activity`() {
        `when`(fragment.activity).thenReturn(null)

        fragment.lockScreen()
        fragment.unlockScreen()

        verify(activity, never()).lockScreen()
        verify(activity, never()).unlockScreen()
    }

    @Test
    fun `verify onBackPressed`() {
        `when`(fragment.activity).thenReturn(activity)

        fragment.onBackPressed()

        verify(activity).onBackPressed()
    }

    @Test
    fun `verify showProgress`() {
        fragment.showProgress(ResponseSubscriptionStatus.SUBSCRIBED)
        fragment.showProgress(ResponseSubscriptionStatus.FINISHED)

        verify(fragment).lockScreen()
        verify(fragment).unlockScreen()
    }
}
