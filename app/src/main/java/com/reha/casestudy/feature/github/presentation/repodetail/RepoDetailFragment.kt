package com.reha.casestudy.feature.github.presentation.repodetail

import android.os.Bundle
import android.view.View
import com.reha.casestudy.R
import com.reha.casestudy.base.BaseFragment
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.databinding.RepoDetailFragmentBinding
import com.reha.casestudy.extension.viewModel

class RepoDetailFragment: BaseFragment<RepoDetailViewModel, RepoDetailFragmentBinding>() {

    companion object {
        fun newInstance(repo: Repo) = RepoDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("repo", repo)
            }
        }
    }

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
        val repo = arguments?.get("repo") as Repo
        observeViewModel()
        initUi(repo)
    }

    private fun observeViewModel() = viewModel.run {

    }

    private fun initUi(repo: Repo) {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        viewModel.setRepoDetail(repo)
    }

    override fun getLayoutId() = R.layout.repo_detail_fragment
}