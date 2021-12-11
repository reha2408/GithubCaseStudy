package com.reha.casestudy

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reha.casestudy.feature.github.presentation.repolist.RepoListFragment
import com.reha.casestudy.util.launchFragmentInHiltContainer
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoListFragmentTest {

    @Test
    fun verify_onCleared() {
        val scenario = launchFragmentInHiltContainer<RepoListFragment>()
    }

}