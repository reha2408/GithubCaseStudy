package com.reha.casestudy.feature.github.presentation.repolist

import com.rtx.framework.base.BaseDisposableObserver
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.response.SearchResultViewEntity

class GithubApiSearchObserver(private val viewModel: RepoListViewModel) : BaseDisposableObserver<List<Repo>>(viewModel) {

    companion object {
        private const val USER_NOT_FOUND_ERROR = "User not found."
    }

    override fun onResponseSuccess(response: List<Repo>?) {
        viewModel.handleSearchList(toViewEntity(response))
    }

    override fun onResponseError(response: List<Repo>?, code: Int) {
        super.onResponseError(response, code)
        if (code == 404) {
            apiErrorMessage = USER_NOT_FOUND_ERROR
        }
        viewModel.handleSearchListError(apiErrorMessage)
    }

    override fun onNetworkError(message: String) {
        super.onNetworkError(message)
        viewModel.handleSearchListError(message)
    }

    override var handleErrorInBase: Boolean = false
    override var handleNetworkErrorInBase: Boolean = false

    private fun toViewEntity(list: List<Repo>?) = SearchResultViewEntity(list?: emptyList())
}