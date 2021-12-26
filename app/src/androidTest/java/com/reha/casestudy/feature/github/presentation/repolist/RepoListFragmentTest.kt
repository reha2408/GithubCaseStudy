package com.reha.casestudy.feature.github.presentation.repolist

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reha.casestudy.launchFragmentInHiltContainer
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoListFragmentTest {

    @Test
    fun fragmentStartWithoutCrash() {
        val scenario = launchFragmentInHiltContainer<RepoListFragment>()
    }

}