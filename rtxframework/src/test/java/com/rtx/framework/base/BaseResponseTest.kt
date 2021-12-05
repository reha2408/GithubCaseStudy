package com.rtx.framework.base

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseResponseTest {

    @Spy
    lateinit var response: BaseResponse

    @Test
    fun `verify BaseResponse is instantiated`() {
        assertNotNull(response)
    }
}
