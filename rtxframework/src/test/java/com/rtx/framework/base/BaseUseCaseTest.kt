package com.rtx.framework.base

import com.nhaarman.mockitokotlin2.mock
import io.reactivex.disposables.Disposable
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Spy

class BaseUseCaseTest : BaseUnitTest() {

    @Spy
    lateinit var useCase: BaseUseCase

    @Test
    fun `verify BaseUseCase is instantiated`() {
        val mock = mock<Disposable>()
        useCase.add(mock)
        useCase.clear()
        assertNotNull(useCase)
    }
}
