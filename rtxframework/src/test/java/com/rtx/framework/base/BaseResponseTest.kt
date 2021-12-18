package com.rtx.framework.base

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Spy

class BaseResponseTest : BaseUnitTest() {

    @Spy
    lateinit var response: BaseResponse

    @Test
    fun `verify BaseResponse is instantiated`() {
        assertNotNull(response)
    }
}
