package com.rtx.framework.base

import org.junit.Assert
import org.junit.Test
import org.mockito.Spy

class BaseRequestTest : BaseUnitTest() {

    @Spy
    lateinit var request: BaseRequest

    @Test
    fun `verify BaseRequest is instantiated`() {
        Assert.assertNotNull(request)
    }
}
