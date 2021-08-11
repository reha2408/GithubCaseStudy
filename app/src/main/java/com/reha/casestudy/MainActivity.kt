package com.reha.casestudy

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import com.reha.casestudy.R
import com.reha.casestudy.base.BaseActivity
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.extension.TAG
import com.reha.casestudy.feature.github.presentation.repodetail.RepoDetailFragment
import com.reha.casestudy.feature.github.presentation.repolist.RepoListFragment


class MainActivity : BaseActivity() {

    private val progressFl by lazy { findViewById<FrameLayout>(R.id.progressbar_fl) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RepoListFragment.newInstance())
                    .commitAllowingStateLoss()
        }
    }

    fun showRepoDetailPage(repo: Repo) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RepoDetailFragment.newInstance(repo) , RepoDetailFragment.TAG)
            .addToBackStack(RepoDetailFragment.TAG)
            .commitAllowingStateLoss()
    }

    override fun lockScreen() {
        progressFl.visibility = VISIBLE
    }

    override fun unlockScreen() {
        progressFl.visibility = INVISIBLE
    }

    override fun onBackPressed() {
        if (!isLoading()) {
            super.onBackPressed()
        }
    }

    fun isLoading() = progressFl.visibility == VISIBLE
}