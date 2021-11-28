package com.rtx.framework.base

enum class ResponseCode(val message: String) {
    SUCCESS("Success"),
    NETWORK_FAIL("Network failed."),
    API_ERROR("Api error occurred.")
}
