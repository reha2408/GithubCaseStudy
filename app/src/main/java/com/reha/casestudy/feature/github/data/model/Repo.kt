package com.reha.casestudy.feature.github.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("open_issues") val openIssues: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("owner") val owner: Owner,
    var isFavorite: Boolean = false
) : Parcelable
