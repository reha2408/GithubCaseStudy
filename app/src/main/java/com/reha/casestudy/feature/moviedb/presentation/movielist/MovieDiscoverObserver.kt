package com.reha.casestudy.feature.moviedb.presentation.movielist

import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.reha.casestudy.feature.moviedb.data.response.MovieDiscoverResponse
import com.rtx.framework.base.BaseDisposableObserver

class MovieDiscoverObserver(
    private val viewModel: MovieHomeViewModel,
    private val discoverType: DiscoverType
) : BaseDisposableObserver<MovieDiscoverResponse>(viewModel) {

    override var handleErrorInBase: Boolean = false
    override var handleNetworkErrorInBase: Boolean = false

    private val userNotFoundMessage = "User not found."

    override fun onResponseSuccess(response: MovieDiscoverResponse?) {
        viewModel.handleMovieCategory(toViewEntity(response))
    }

    override fun onResponseError(response: MovieDiscoverResponse?, code: Int) {
        super.onResponseError(response, code)
        if (code == 404) {
            apiErrorMessage = userNotFoundMessage
        }
        // viewModel.handleSearchListError(apiErrorMessage)
    }

    override fun onNetworkError(message: String) {
        super.onNetworkError(message)
        // viewModel.handleSearchListError(message)
    }

    private fun toViewEntity(response: MovieDiscoverResponse?) =
        MovieCategory(discoverType, response?.results?.toMutableList() ?: mutableListOf(), response?.page)
}
