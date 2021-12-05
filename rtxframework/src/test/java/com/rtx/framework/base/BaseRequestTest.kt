package com.rtx.framework.base

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseRequestTest {

    @Spy
    lateinit var request: BaseRequest

    @Test
    fun `verify BaseRequest is instantiated`() {
        Assert.assertNotNull(request)
    }
}
