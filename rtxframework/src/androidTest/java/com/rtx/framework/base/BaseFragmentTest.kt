package com.rtx.framework.base

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rtx.framework.dummy.DummyFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseFragmentTest {

    @Test
    fun verify_onCleared() {
        val scenario = launchFragmentInContainer<DummyFragment>()
    }

}
