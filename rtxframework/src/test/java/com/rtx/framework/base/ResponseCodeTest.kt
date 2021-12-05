package com.rtx.framework.base

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResponseCodeTest {

    @Test
    fun `check 'message' when type is set to 'Success'`() {
        val type = ResponseCode.SUCCESS
        val message = type.message
        assertEquals("Success", message)
    }

    @Test
    fun `check 'message' when type is set to 'Network Fail'`() {
        val type = ResponseCode.NETWORK_FAIL
        val message = type.message
        assertEquals("Network failed.", message)
    }

    @Test
    fun `check 'message' when type is set to 'Api Error'`() {
        val type = ResponseCode.API_ERROR
        val message = type.message
        assertEquals("Api error occurred.", message)
    }
}