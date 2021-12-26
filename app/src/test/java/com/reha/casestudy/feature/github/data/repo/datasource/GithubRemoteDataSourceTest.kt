package com.reha.casestudy.feature.github.data.repo.datasource

import com.nhaarman.mockitokotlin2.verify
import com.reha.casestudy.BaseUnitTest
import com.reha.casestudy.feature.github.data.GitHubApiService
import com.reha.casestudy.feature.github.data.request.SearchRequest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy

class GithubRemoteDataSourceTest : BaseUnitTest() {

    @Mock
    lateinit var gitHubApiService: GitHubApiService

    @InjectMocks
    @Spy
    lateinit var githubRemoteDataSource: GithubRemoteDataSource

    @Test
    fun `verify github api service is called`() {
        // given
        val request = SearchRequest()

        // when
        githubRemoteDataSource.search(request)

        // then
        verify(gitHubApiService).search(request)
    }
}
