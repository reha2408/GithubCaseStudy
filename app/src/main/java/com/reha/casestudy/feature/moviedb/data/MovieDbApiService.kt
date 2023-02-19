package com.reha.casestudy.feature.moviedb.data

import com.reha.casestudy.feature.moviedb.data.request.MovieDiscoverRequest
import com.reha.casestudy.feature.moviedb.data.response.MovieDiscoverResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieDbApiService {

    @GET("discover/movie")
    fun discover(@QueryMap movieDiscoverRequest: MovieDiscoverRequest): Single<Response<MovieDiscoverResponse>>

    // https://api.themoviedb.org/3/discover/movie?api_key=30b17f687f45dc52fc15a2dcf7f03695&language=en-US&sort_by=popularity.desc
}
