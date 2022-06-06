package com.reha.casestudy.feature.moviedb.data.repo.datasource

import com.reha.casestudy.feature.moviedb.data.MovieDbApiService
import com.reha.casestudy.feature.moviedb.data.request.MovieDiscoverRequest
import javax.inject.Inject

class MovieDbRemoteDataSource @Inject constructor(
    private val service: MovieDbApiService
) {
    fun discover(request: MovieDiscoverRequest) = service.discover(request)
}