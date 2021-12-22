package com.reha.casestudy.feature.github.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    @SerializedName("id") val id: Long?= null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("open_issues") val openIssues: String? = null,
    @SerializedName("stargazers_count") val stargazersCount: Int? = null,
    @SerializedName("owner") val owner: Owner? = null,
    var isFavorite: Boolean = false
) : Parcelable
