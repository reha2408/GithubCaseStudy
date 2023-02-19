package com.reha.casestudy.feature.moviedb.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.rtx.framework.base.BaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDiscoverResponse(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<Movie>? = null
) : BaseResponse(), Parcelable
