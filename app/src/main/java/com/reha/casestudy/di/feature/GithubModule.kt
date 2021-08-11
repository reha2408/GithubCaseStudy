package com.reha.casestudy.di.feature

import com.reha.casestudy.feature.github.data.GitHubApiService
import com.reha.casestudy.feature.github.data.repo.GitHubDataRepository
import com.reha.casestudy.feature.github.domain.GitHubRepository
import com.reha.casestudy.feature.github.data.repo.datasource.GithubRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class GithubModule {

    @Provides
    fun provideGithubRemoteDataSource(service: GitHubApiService): GithubRemoteDataSource = GithubRemoteDataSource(service)

    @Provides
    fun provideGithubRepository(remoteDataSource: GithubRemoteDataSource): GitHubRepository = GitHubDataRepository(remoteDataSource)
}