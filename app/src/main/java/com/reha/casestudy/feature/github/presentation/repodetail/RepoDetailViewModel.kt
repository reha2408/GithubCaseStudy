package com.reha.casestudy.feature.github.presentation.repodetail

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import com.reha.casestudy.feature.github.data.model.Repo
import com.rtx.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(val pref: SharedPreferences) : BaseViewModel() {

    val repoDetail = ObservableField<Repo>()

    fun setRepoDetail(repo: Repo) = repoDetail.set(repo)

    fun onFavoriteClicked() {
        repoDetail.get()?.let {
            it.isFavorite = !it.isFavorite
            pref.edit().putBoolean(it.id.toString(), it.isFavorite).apply()
            repoDetail.notifyChange()
        }
    }
}
