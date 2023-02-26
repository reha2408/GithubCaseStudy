package com.reha.casestudy.feature.github2.domain

import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.request.SearchRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GitHubRepository {
    suspend fun search(request: SearchRequest): Flow<Response<List<Repo>>>
}
