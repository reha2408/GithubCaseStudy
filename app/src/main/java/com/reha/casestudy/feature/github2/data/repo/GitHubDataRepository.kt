package com.reha.casestudy.feature.github2.data.repo

import com.reha.casestudy.feature.github2.data.repo.datasource.GithubRemoteDataSource
import com.reha.casestudy.feature.github.data.request.SearchRequest
import com.reha.casestudy.feature.github2.domain.GitHubRepository
import javax.inject.Inject

class GitHubDataRepository @Inject constructor(
    private val remoteDataSource: GithubRemoteDataSource
) : GitHubRepository {
    override suspend fun search(request: SearchRequest) = remoteDataSource.search(request)
}
