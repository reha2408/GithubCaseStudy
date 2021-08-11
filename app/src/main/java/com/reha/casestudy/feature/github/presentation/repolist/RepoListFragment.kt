package com.reha.casestudy.feature.github.presentation.repolist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.reha.casestudy.MainActivity
import com.reha.casestudy.R
import com.reha.casestudy.base.BaseFragment
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.databinding.RepoListFragmentBinding
import com.reha.casestudy.extension.hideSoftKeyboard
import com.reha.casestudy.extension.observeLiveData
import com.reha.casestudy.extension.showSoftKeyboard
import com.reha.casestudy.extension.viewModel

class RepoListFragment: BaseFragment<RepoListViewModel, RepoListFragmentBinding>() {

    companion object {
        fun newInstance() = RepoListFragment()
    }

    private val repoAdapter =  RepoAdapter(::onRepoSelected)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = viewModel(viewModelFactory) {
            // do something with viewmodel.
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
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
        (activity as MainActivity).showRepoDetailPage(item)
    }

    override fun getLayoutId() = R.layout.repo_list_fragment
}