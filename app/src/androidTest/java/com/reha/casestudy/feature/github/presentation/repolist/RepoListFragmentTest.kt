package com.reha.casestudy.feature.github.presentation.repolist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.google.gson.Gson
import com.reha.casestudy.BaseUiTest
import com.reha.casestudy.R
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.domain.FakeGithubRepository
import com.reha.casestudy.launchFragmentInHiltContainer
import com.reha.casestudy.withListSize
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class RepoListFragmentTest : BaseUiTest() {

    @Inject
    lateinit var gson: Gson

    @Test
    fun fragmentStartWithoutCrash() {
        launchFragmentInHiltContainer<RepoListFragment>()
    }

    @Test
    fun onSearchResultedWithTwoItemsInList() {
        launchFragmentInHiltContainer<RepoListFragment> {
            getRepository(this).run {
                list = gson.fromJson(twoItemList, Array<Repo>::class.java).toMutableList()
            }
        }

        onView(withId(R.id.submitBtn)).check(matches(isDisplayed()))

        // Type text and then press the button.
        onView(withId(R.id.searchEt))
            .perform(typeText("reha2408"), closeSoftKeyboard())

        onView(withId(R.id.submitBtn)).perform(click())
        onView(withId(R.id.repoList)).check(matches(withListSize(2)))
    }

    @Test
    fun onSearchResultedWithEmptyList() {
        launchFragmentInHiltContainer<RepoListFragment> {
            getRepository(this).run {
                list = gson.fromJson(emptyList, Array<Repo>::class.java).toMutableList()
            }
        }

        onView(withId(R.id.submitBtn)).check(matches(isDisplayed()))

        // Type text and then press the button.
        onView(withId(R.id.searchEt))
            .perform(typeText("reha2408"), closeSoftKeyboard())

        onView(withId(R.id.submitBtn)).perform(click())
        onView(withId(R.id.repoList)).check(matches(withListSize(0)))
    }

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        launchFragmentInHiltContainer<RepoListFragment>()
        // Attempt to scroll to an item that contains the special text.
        onView(withId(R.id.repoList)) // scrollTo will fail the test if no item matches.
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("not in the list"))
                )
            )
    }

    private val twoItemList = """[
    {
        "id": 339011582,
        "name": "android-dev-challenge-compose",
        "description": "Repo hosting default community health files.",
        "owner": {
            "id": 32689599,
            "avatar_url": "https://avatars.githubusercontent.com/u/32689599?v=4"
        },
        "stargazers_count": 459,
        "open_issues": 4
    },
    {
        "id": 112380358,
        "name": "android-ktx",
        "description": "A set of Kotlin extensions for Android app development.",
        "owner": {
            "id": 32689599,
            "avatar_url": "https://avatars.githubusercontent.com/u/32689599?v=4"
        },
        "stargazers_count": 7549,
        "open_issues": 88
    }
    ] 
    """.trimIndent()

    private val emptyList = """[
        ]
    """.trimIndent()

    private fun getRepository(repoListFragment: RepoListFragment) =
        (repoListFragment.viewModel.githubApiSearch.repository) as FakeGithubRepository
}
