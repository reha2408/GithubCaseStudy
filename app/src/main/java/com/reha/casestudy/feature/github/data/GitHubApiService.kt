package com.reha.casestudy.feature.github.data

import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.request.SearchRequest
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("users/{user}/repos")
    fun search(@Path("user") searchRequest: SearchRequest): Single<Response<List<Repo>>>
}
