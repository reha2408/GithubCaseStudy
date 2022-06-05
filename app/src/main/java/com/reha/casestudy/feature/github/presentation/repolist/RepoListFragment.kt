package com.reha.casestudy.feature.github.presentation.repolist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.reha.casestudy.BR
import com.reha.casestudy.feature.github.presentation.GithubMainActivity
import com.reha.casestudy.R
import com.reha.casestudy.databinding.RepoListFragmentBinding
import com.reha.casestudy.feature.github.data.model.Repo
import com.rtx.framework.base.BaseFragment
import com.rtx.framework.extension.hideSoftKeyboard
import com.rtx.framework.extension.observeLiveData
import com.rtx.framework.extension.showSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : BaseFragment<RepoListViewModel, RepoListFragmentBinding>() {

    private val repoAdapter = RepoAdapter(::onRepoSelected)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initUi()
    }

    private fun observeViewModel() = viewModel.run {
        observeLiveData(repoListLiveData) {
            repoAdapter.submitList(it)
        }
    }

    private fun initUi() {
        val llManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.run {
            repoList.layoutManager = llManager
            repoList.adapter = repoAdapter
            submitBtn.setOnClickListener {
                hideSoftKeyboard(true)
                searchEt.text.toString().takeIf {
                    it.isNotBlank() && it.isNotEmpty()
                }?.let { this@RepoListFragment.viewModel.searchList(it) }
            }
            searchEt.showSoftKeyboard()
        }
    }

    private fun onRepoSelected(item: Repo) {
        (activity as GithubMainActivity).showRepoDetailPage(item)
    }

    override fun getLayoutId() = R.layout.repo_list_fragment

    override fun getBRViewModelId() = BR.viewModel

    override fun getViewModelClass() = RepoListViewModel::class.java
}
