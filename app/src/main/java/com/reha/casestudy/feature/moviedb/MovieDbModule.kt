package com.reha.casestudy.feature.moviedb

import com.reha.casestudy.feature.moviedb.data.MovieDbApiService
import com.reha.casestudy.feature.moviedb.data.repo.MovieDbDataRepository
import com.reha.casestudy.feature.moviedb.data.repo.datasource.MovieDbRemoteDataSource
import com.reha.casestudy.feature.moviedb.domain.MovieDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
class MovieDbModule {

    @Provides
    @ViewModelScoped
    fun provideMovieDbApiService(retrofit: Retrofit): MovieDbApiService {
        return retrofit.create(MovieDbApiService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideMovieDbRepository(remoteDataSource: MovieDbRemoteDataSource): MovieDbRepository =
        MovieDbDataRepository(remoteDataSource)

    @Provides
    @ViewModelScoped
    fun provideMovieDbRemoteDataSource(service: MovieDbApiService): MovieDbRemoteDataSource =
        MovieDbRemoteDataSource(service)
}