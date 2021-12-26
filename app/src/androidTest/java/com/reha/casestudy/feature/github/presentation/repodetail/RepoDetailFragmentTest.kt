package com.reha.casestudy.feature.github.presentation.repodetail

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.reha.casestudy.R
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class RepoDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    @Test
    fun fragmentStartWithoutCrash() {
        val scenario = launchFragmentInHiltContainer<RepoDetailFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable("repo", Repo(name = "CaseStudy", isFavorite = true))
            }
        )
        onView(withId(R.id.name))
            .check(matches(isDisplayed()))
            .check(matches(withText("CaseStudy")))
    }
}
