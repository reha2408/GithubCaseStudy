package com.reha.casestudy.feature.github.presentation

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import com.reha.casestudy.R
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.presentation.repodetail.RepoDetailFragment
import com.reha.casestudy.feature.github.presentation.repolist.RepoListFragment
import com.rtx.framework.base.BaseActivity
import com.rtx.framework.extension.TAG
import com.rtx.framework.extension.newInstance
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubMainActivity : BaseActivity() {

    private val progressFl by lazy { findViewById<FrameLayout>(R.id.progressbar_fl) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.github_main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, newInstance<RepoListFragment>())
                .commitAllowingStateLoss()
        }
    }

    fun showRepoDetailPage(repo: Repo) {
        val fragment = newInstance<RepoDetailFragment>("repo" to repo)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment.TAG)
            .addToBackStack(fragment.TAG)
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
