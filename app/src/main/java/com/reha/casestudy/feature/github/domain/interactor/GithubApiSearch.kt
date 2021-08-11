package com.reha.casestudy.feature.github.domain.interactor

import com.reha.casestudy.base.RequestUseCase
import com.reha.casestudy.feature.github.domain.GitHubRepository
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.request.SearchRequest
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GithubApiSearch @Inject constructor(private val repository: GitHubRepository) :
    RequestUseCase<List<Repo>, GithubApiSearch.Params>() {

    override fun buildUseCaseObservable(params: Params): Single<Response<List<Repo>>> {
        val request = SearchRequest().apply {
            name = params.name
        }
        return repository.search(request)
    }

    data class Params(
        val name: String?
    )
}