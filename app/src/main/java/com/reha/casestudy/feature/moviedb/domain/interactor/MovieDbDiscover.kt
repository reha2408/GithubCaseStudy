package com.reha.casestudy.feature.moviedb.domain.interactor

import com.reha.casestudy.feature.moviedb.data.request.MovieDiscoverRequest
import com.reha.casestudy.feature.moviedb.data.response.MovieDiscoverResponse
import com.reha.casestudy.feature.moviedb.domain.MovieDbRepository
import com.reha.casestudy.feature.moviedb.presentation.movielist.DiscoverType
import com.rtx.framework.base.RequestUseCase
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MovieDbDiscover @Inject constructor(
    val repository: MovieDbRepository
) : RequestUseCase<MovieDiscoverResponse, MovieDbDiscover.Params>() {

    companion object {
        const val apiKey: String = "30b17f687f45dc52fc15a2dcf7f03695"
        const val lang: String = "en-US"
    }

    override fun buildUseCaseObservable(params: Params): Single<Response<MovieDiscoverResponse>> {
        val request = MovieDiscoverRequest().apply {
            put("api_key", apiKey)
            put("language", lang)
            put("sort_by", getQueryStringByDiscoverType(params.discoverType))
        }
        return repository.discover(request)
    }

    private fun getQueryStringByDiscoverType(discoverType: DiscoverType) = when(discoverType) {
            DiscoverType.POPULAR -> "popularity.desc"
            DiscoverType.REVENUE -> "revenue.desc"
            DiscoverType.TOP_RATED -> "vote_average.desc"
    }

    data class Params(
        val discoverType: DiscoverType
    )
}
