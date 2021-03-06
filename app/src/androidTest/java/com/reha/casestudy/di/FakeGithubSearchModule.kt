package com.reha.casestudy.di

import com.reha.casestudy.feature.github.GithubSearchModule
import com.reha.casestudy.feature.github.domain.FakeGithubRepository
import com.reha.casestudy.feature.github.domain.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.testing.TestInstallIn

/**
 * TasksRepository binding to use in tests.
 *
 * Hilt will inject a [FakeRepository] instead of a [GitHubRepository].
 */
@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [GithubSearchModule::class]
)
class FakeGithubSearchModule {

    @Provides
    @ViewModelScoped
    fun provideGithubRepository(): GitHubRepository = FakeGithubRepository()
}
