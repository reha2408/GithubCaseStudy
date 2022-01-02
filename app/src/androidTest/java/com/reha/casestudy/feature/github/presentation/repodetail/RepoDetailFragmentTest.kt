package com.reha.casestudy.feature.github.presentation.repodetail

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.reha.casestudy.BaseUiTest
import com.reha.casestudy.R
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@MediumTest
@HiltAndroidTest
class RepoDetailFragmentTest : BaseUiTest() {

    @Test
    fun fragmentStartWithoutCrash() {
        launchFragmentInHiltContainer<RepoDetailFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable("repo", Repo(name = "CaseStudy", isFavorite = true))
            }
        )
    }

    @Test
    fun showRepoDetailWithName() {
        launchFragmentInHiltContainer<RepoDetailFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable("repo", Repo(name = "CaseStudy", isFavorite = true))
            }
        )
        onView(withId(R.id.name))
            .check(matches(isDisplayed()))
            .check(matches(withText("CaseStudy")))
    }
}
