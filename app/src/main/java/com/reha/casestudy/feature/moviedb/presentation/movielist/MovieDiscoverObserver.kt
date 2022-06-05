package com.reha.casestudy.feature.moviedb.presentation.movielist

import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.response.SearchResultViewEntity
import com.rtx.framework.base.BaseDisposableObserver

class MovieDiscoverObserver(
    private val viewModel: MovieHomeViewModel
) : BaseDisposableObserver<List<Repo>>(viewModel) {

    override var handleErrorInBase: Boolean = false
    override var handleNetworkErrorInBase: Boolean = false

    private val userNotFoundMessage = "User not found."

    override fun onResponseSuccess(response: List<Repo>?) {
        viewModel.handleSearchList(toViewEntity(response))
    }

    override fun onResponseError(response: List<Repo>?, code: Int) {
        super.onResponseError(response, code)
        if (code == 404) {
            apiErrorMessage = userNotFoundMessage
        }
        viewModel.handleSearchListError(apiErrorMessage)
    }

    override fun onNetworkError(message: String) {
        super.onNetworkError(message)
        viewModel.handleSearchListError(message)
    }

    private fun toViewEntity(list: List<Repo>?) = SearchResultViewEntity(list ?: emptyList())
}
