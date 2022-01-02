package com.reha.casestudy.feature.github.domain

import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.request.SearchRequest
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class FakeGithubRepository @Inject constructor() : GitHubRepository {

    var list = emptyList<Repo>()

    override fun search(request: SearchRequest): Single<Response<List<Repo>>> {
        val response = Response.success(list)
        return Single.just(response)
    }
}
