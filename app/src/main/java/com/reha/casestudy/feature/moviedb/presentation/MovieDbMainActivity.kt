package com.reha.casestudy.feature.moviedb.presentation

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import com.reha.casestudy.R
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.reha.casestudy.feature.moviedb.presentation.moviedetail.MovieDetailFragment
import com.reha.casestudy.feature.moviedb.presentation.movielist.MovieHomeFragment
import com.rtx.framework.base.BaseActivity
import com.rtx.framework.extension.TAG
import com.rtx.framework.extension.newInstance
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDbMainActivity : BaseActivity() {

    private val progressFl by lazy { findViewById<FrameLayout>(R.id.progressbar_fl) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.github_main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, newInstance<MovieHomeFragment>())
                .commitAllowingStateLoss()
        }
    }

    fun showMovieDetailPage(movie: Movie) {
        val fragment = newInstance<MovieDetailFragment>("movie" to movie)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, fragment.TAG)
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
