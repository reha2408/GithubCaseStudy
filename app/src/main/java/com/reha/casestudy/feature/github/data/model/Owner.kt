package com.reha.casestudy.feature.github.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url") val avatarUrl: String
) : Parcelable
