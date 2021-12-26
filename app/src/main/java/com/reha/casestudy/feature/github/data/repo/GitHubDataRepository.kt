package com.reha.casestudy.feature.github.data.repo

import com.reha.casestudy.feature.github.data.repo.datasource.GithubRemoteDataSource
import com.reha.casestudy.feature.github.data.request.SearchRequest
import com.reha.casestudy.feature.github.domain.GitHubRepository
import javax.inject.Inject

class GitHubDataRepository @Inject constructor(
    private val remoteDataSource: GithubRemoteDataSource
) : GitHubRepository {
    override fun search(request: SearchRequest) = remoteDataSource.search(request)
}
