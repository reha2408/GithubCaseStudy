package com.reha.casestudy.feature.github2.domain.interactor

import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.request.SearchRequest
import com.reha.casestudy.feature.github2.domain.GitHubRepository
import com.rtx.framework.base.FlowRequestUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GithubApiSearchFlow @Inject constructor(
    val repository: GitHubRepository
) : FlowRequestUseCase<List<Repo>, GithubApiSearchFlow.Params>() {

    override suspend fun buildFlowObservable(params: Params): Flow<Response<List<Repo>>> {
        val request = SearchRequest().apply {
            name = params.name
        }
        return repository.search(request)
    }

    data class Params(
        val name: String?
    )
}
