package com.reha.casestudy.feature.github.presentation.repolist

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reha.casestudy.launchFragmentInHiltContainer
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.filters.MediumTest
import com.reha.casestudy.R
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class RepoListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    @Test
    fun fragmentStartWithoutCrash() {
        val scenario = launchFragmentInHiltContainer<RepoListFragment>()

        onView(withId(R.id.submitBtn)).check(matches(isDisplayed()))

        // Type text and then press the button.
        onView(withId(R.id.searchEt))
            .perform(typeText("reha2408"), closeSoftKeyboard())
        onView(withId(R.id.submitBtn)).perform(click())
    }
}
