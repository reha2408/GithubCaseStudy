package com.reha.casestudy.feature.moviedb.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("img_url") val imageUrl: String
) : Parcelable