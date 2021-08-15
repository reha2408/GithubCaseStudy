package com.reha.casestudy.feature.jetpackcompose.data

import com.reha.casestudy.R

data class Message(
    val author: String,
    val body: String,
    var lastOnline: String = ""
)
