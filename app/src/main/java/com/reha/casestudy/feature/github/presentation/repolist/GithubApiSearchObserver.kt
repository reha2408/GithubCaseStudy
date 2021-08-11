package com.reha.casestudy.feature.github.presentation.repolist

import com.reha.casestudy.base.BaseDisposableObserver
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.response.SearchResultViewEntity

class GithubApiSearchObserver(private val viewModel: RepoListViewModel) : BaseDisposableObserver<List<Repo>>(viewModel) {

    override fun onResponseSuccess(response: List<Repo>?) {
        viewModel.handleSearchList(toViewEntity(response))
    }

    override fun onResponseError(errorMessage: String) {
        viewModel.handleSearchListError(errorMessage)
    }

    private fun toViewEntity(list: List<Repo>?) = SearchResultViewEntity(list?: emptyList())
}