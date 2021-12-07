package com.reha.casestudy

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reha.casestudy.extension.launchFragmentInHiltContainer
import com.reha.casestudy.feature.github.presentation.repolist.RepoListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseFragmentTest {

    @Test
    fun verify_onCleared() {
        val scenario = launchFragmentInHiltContainer<RepoListFragment>()
    }

    companion object {
        // class DummyFragment: BaseFragment<BaseViewModel>
    }
}