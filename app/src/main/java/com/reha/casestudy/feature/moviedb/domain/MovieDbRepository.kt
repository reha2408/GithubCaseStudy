package com.reha.casestudy.feature.moviedb.domain

import com.reha.casestudy.feature.moviedb.data.request.MovieDiscoverRequest
import com.reha.casestudy.feature.moviedb.data.response.MovieDiscoverResponse
import io.reactivex.Single
import retrofit2.Response

interface MovieDbRepository {
    fun discover(request: MovieDiscoverRequest): Single<Response<MovieDiscoverResponse>>
}