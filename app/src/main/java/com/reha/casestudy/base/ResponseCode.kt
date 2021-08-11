package com.reha.casestudy.base

enum class ResponseCode(val message: String) {
    SUCCESS("Success"),
    NETWORK_FAIL("Network failed."),
    API_ERROR("Github Api error occurred."),
    NOT_FOUND_ERROR("User not found.")
}


