package com.reha.casestudy.feature.github2

import com.reha.casestudy.feature.github2.data.GitHubApiService
import com.reha.casestudy.feature.github2.data.repo.GitHubDataRepository
import com.reha.casestudy.feature.github2.data.repo.datasource.GithubRemoteDataSource
import com.reha.casestudy.feature.github2.domain.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class GithubSearchModule {

    @Provides
    @ViewModelScoped
    fun provideGithubApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideGithubRepository(remoteDataSource: GithubRemoteDataSource): GitHubRepository =
        GitHubDataRepository(remoteDataSource)

    @Provides
    @ViewModelScoped
    fun provideGithubRemoteDataSource(service: GitHubApiService): GithubRemoteDataSource =
        GithubRemoteDataSource(service)
}
