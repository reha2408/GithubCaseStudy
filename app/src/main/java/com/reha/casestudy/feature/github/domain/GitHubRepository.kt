package com.reha.casestudy.feature.github.domain

import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.request.SearchRequest
import io.reactivex.Single
import retrofit2.Response

interface GitHubRepository {
    fun search(request: SearchRequest): Single<Response<List<Repo>>>
}
