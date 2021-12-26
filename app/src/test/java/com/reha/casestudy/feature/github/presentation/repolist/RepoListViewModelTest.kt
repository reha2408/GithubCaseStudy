package com.reha.casestudy.feature.github.presentation.repolist

import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.reha.casestudy.BaseUnitTest
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.response.SearchResultViewEntity
import com.reha.casestudy.feature.github.domain.interactor.GithubApiSearch
import com.reha.casestudy.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy

class RepoListViewModelTest : BaseUnitTest() {

    @Mock
    lateinit var githubApiSearch: GithubApiSearch

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @InjectMocks
    @Spy
    lateinit var vm: RepoListViewModel

    @Test
    fun `verify search success on usecase`() {
        // given
        val searchText = "reha2408"

        // when
        vm.searchList(searchText)

        // then
        verify(githubApiSearch).execute(any(), any())
    }

    @Test
    fun `verify search error on usecase`() {
        // given
        val message = "test"

        // when
        vm.handleSearchListError(message)

        // then
        assertEquals(vm.noDataText.get(), message)
        assertEquals(vm.isNoData.get(), true)
    }

    @Test
    fun `verify handle non-empty list`() {
        // given
        val list = listOf(Repo(), Repo())

        // when
        vm.handleSearchList(SearchResultViewEntity(list))

        // then
        assertEquals(vm.repoListLiveData.getOrAwaitValue(), list)
        assertEquals(vm.isNoData.get(), false)
    }

    @Test
    fun `verify handle empty list`() {
        // given
        val list = emptyList<Repo>()
        val entity = SearchResultViewEntity(list)

        // when
        vm.handleSearchList(entity)

        // then
        assertEquals(vm.repoListLiveData.getOrAwaitValue(), list)
        assertEquals(vm.noDataText.get(), "User has no repository on Github.")
        assertEquals(vm.isNoData.get(), true)
    }
}
