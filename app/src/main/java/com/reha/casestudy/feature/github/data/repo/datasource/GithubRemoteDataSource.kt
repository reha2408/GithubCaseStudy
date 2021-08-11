package com.reha.casestudy.feature.github.data.repo.datasource

import com.reha.casestudy.feature.github.data.GitHubApiService
import com.reha.casestudy.feature.github.data.request.SearchRequest
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val service: GitHubApiService
) {
    fun search(request: SearchRequest) = service.search(request)
}