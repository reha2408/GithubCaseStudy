package com.reha.casestudy.feature.github.presentation.repodetail

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.launchFragmentInHiltContainer
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDetailFragmentTest {

    @Test
    fun fragmentStartWithoutCrash() {
        val scenario = launchFragmentInHiltContainer<RepoDetailFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable("repo", Repo())
            }
        )
    }
}
