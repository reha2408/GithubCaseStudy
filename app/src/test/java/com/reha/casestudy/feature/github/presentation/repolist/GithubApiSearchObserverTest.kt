package com.reha.casestudy.feature.github.presentation.repolist

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.reha.casestudy.BaseUnitTest
import com.reha.casestudy.feature.github.data.model.Owner
import com.reha.casestudy.feature.github.data.model.Repo
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import java.net.HttpURLConnection

class GithubApiSearchObserverTest: BaseUnitTest() {

    private val test = "test"
    private val repo = Repo(1, test, test, test, 1, Owner(1, test), true)

    @Mock
    lateinit var viewModel: RepoListViewModel

    @InjectMocks
    @Spy
    lateinit var githubApiSearchObserver: GithubApiSearchObserver

    @Test
    fun onResponseSuccess() {
        // given
        val list = listOf(repo, repo)

        // when
        githubApiSearchObserver.onResponseSuccess(list)

        // then
        verify(viewModel).handleSearchList(any())
    }

    @Test
    fun onResponseError() {
        // given
        val list = listOf(repo, repo)

        // when
        githubApiSearchObserver.onResponseError(list, HttpURLConnection.HTTP_BAD_REQUEST)

        // then
        verify(viewModel).handleSearchListError(any())
    }

    @Test
    fun onNetworkError() {
        // when
        githubApiSearchObserver.onNetworkError(test)

        // then
        verify(viewModel).handleSearchListError(any())
    }
}