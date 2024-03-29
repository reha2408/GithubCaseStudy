package com.reha.casestudy.feature.github.presentation.repolist

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.response.SearchResultViewEntity
import com.reha.casestudy.feature.github.domain.interactor.GithubApiSearch
import com.reha.casestudy.feature.github2.domain.interactor.GithubApiSearchFlow
import com.reha.casestudy.feature.github2.presentation.GithubApiSearchFlowObserver
import com.rtx.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    val githubApiSearch: GithubApiSearch,
    val githubApiSearchFlow: GithubApiSearchFlow,
    private val pref: SharedPreferences
) : BaseViewModel() {

    val isNoData = ObservableField<Boolean>(false)
    val noDataText = ObservableField("")

    private val repoList = MutableLiveData<List<Repo>>()
    val repoListLiveData: LiveData<List<Repo>> get() = repoList

    fun searchList(searchText: String, useFlow: Boolean = true) {
        if (useFlow) {
            githubApiSearchFlow.execute(GithubApiSearchFlowObserver(this), GithubApiSearchFlow.Params(searchText))
        } else {
            githubApiSearch.execute(GithubApiSearchObserver(this), GithubApiSearch.Params(searchText))
        }
    }

    fun handleSearchListError(errorMessage: String) {
        noDataText.set(errorMessage)
        isNoData.set(true)
    }

    fun handleSearchList(entity: SearchResultViewEntity) {
        entity.items.takeIf { it.isNotEmpty() }?.forEach {
            it.isFavorite = pref.getBoolean(it.id.toString(), false)
        } ?: noDataText.set("User has no repository on Github.")
        repoList.value = entity.items
        isNoData.set((entity.items.isEmpty()))
    }
}
