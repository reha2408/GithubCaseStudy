package com.reha.casestudy.feature.github.presentation.repolist

import com.reha.casestudy.launchFragmentInHiltContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoListFragmentTest {

    @Test
    fun verify_onCleared() {
        val scenario = launchFragmentInHiltContainer<RepoListFragment>()
    }

}