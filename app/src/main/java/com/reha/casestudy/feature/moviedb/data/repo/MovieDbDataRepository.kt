package com.reha.casestudy.feature.moviedb.data.repo

import com.reha.casestudy.feature.moviedb.data.repo.datasource.MovieDbRemoteDataSource
import com.reha.casestudy.feature.moviedb.data.request.MovieDiscoverRequest
import com.reha.casestudy.feature.moviedb.data.response.MovieDiscoverResponse
import com.reha.casestudy.feature.moviedb.domain.MovieDbRepository
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MovieDbDataRepository @Inject constructor(
    private val remoteDataSource: MovieDbRemoteDataSource
) : MovieDbRepository {

    override fun discover(request: MovieDiscoverRequest): Single<Response<MovieDiscoverResponse>> =
        remoteDataSource.discover(request)
}
