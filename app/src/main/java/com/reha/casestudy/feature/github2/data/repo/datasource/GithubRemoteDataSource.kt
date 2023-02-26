package com.reha.casestudy.feature.github2.data.repo.datasource

import com.reha.casestudy.feature.github.data.request.SearchRequest
import com.reha.casestudy.feature.github2.data.GitHubApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val service: GitHubApiService
) {
    suspend fun search(request: SearchRequest) = flow {
        emit(service.search(request))
    }
}
