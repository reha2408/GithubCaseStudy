package com.reha.casestudy.feature.github2.data

import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.request.SearchRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("users/{user}/repos")
    suspend fun search(@Path("user") searchRequest: SearchRequest): Response<List<Repo>>
}
