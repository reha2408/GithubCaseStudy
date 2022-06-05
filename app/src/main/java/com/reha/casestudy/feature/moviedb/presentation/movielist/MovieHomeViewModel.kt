package com.reha.casestudy.feature.moviedb.presentation.movielist

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.response.SearchResultViewEntity
import com.reha.casestudy.feature.github.domain.interactor.GithubApiSearch
import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.rtx.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    val githubApiSearch: GithubApiSearch,
    private val pref: SharedPreferences
) : BaseViewModel() {

    val isNoData = ObservableField<Boolean>(false)
    val noDataText = ObservableField("")

    private val movieHomeList = MutableLiveData<List<MovieCategory>>()
    val movieHomeListLiveData: LiveData<List<MovieCategory>> get() = movieHomeList

    fun searchList(searchText: String) {
        githubApiSearch.execute(MovieDiscoverObserver(this), GithubApiSearch.Params(searchText))
    }

    fun handleSearchListError(errorMessage: String) {
        noDataText.set(errorMessage)
        isNoData.set(true)
    }

    fun handleSearchList(entity: SearchResultViewEntity) {
        entity.items.takeIf { it.isNotEmpty() }?.forEach {
            it.isFavorite = pref.getBoolean(it.id.toString(), false)
        } ?: noDataText.set("User has no repository on Github.")
        // movieHomeList.value = entity.items
        isNoData.set((entity.items.isEmpty()))
    }
}
