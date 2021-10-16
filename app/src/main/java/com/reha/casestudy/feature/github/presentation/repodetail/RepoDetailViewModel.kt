package com.reha.casestudy.feature.github.presentation.repodetail

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import com.rtx.framework.base.BaseViewModel
import com.reha.casestudy.feature.github.data.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(val pref: SharedPreferences) : BaseViewModel() {

    var repoDetail = ObservableField<Repo>()

    fun setRepoDetail(repo: Repo) = repoDetail.set(repo)

    fun onFavoriteClicked() {
        repoDetail.get()?.let {
            it.isFavorite = !it.isFavorite
            pref.edit().putBoolean(it.id.toString(), it.isFavorite).apply()
            repoDetail.notifyChange()
        }
    }
}