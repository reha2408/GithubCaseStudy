package com.reha.casestudy.feature.github.data.repo

import com.nhaarman.mockitokotlin2.verify
import com.reha.casestudy.BaseUnitTest
import com.reha.casestudy.feature.github.data.repo.datasource.GithubRemoteDataSource
import com.reha.casestudy.feature.github.data.request.SearchRequest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy

class GitHubDataRepositoryTest : BaseUnitTest() {

    @Mock
    lateinit var githubRemoteDataSource: GithubRemoteDataSource

    @InjectMocks
    @Spy
    lateinit var gitHubDataRepository: GitHubDataRepository

    @Test
    fun `verify remote data source is called`() {
        // given
        val request = SearchRequest()

        // when
        gitHubDataRepository.search(request)

        // then
        verify(githubRemoteDataSource).search(request)
    }
}
