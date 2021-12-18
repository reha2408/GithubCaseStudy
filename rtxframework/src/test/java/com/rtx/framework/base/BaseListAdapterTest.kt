package com.rtx.framework.base

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.rtx.framework.dummy.DummyModel
import org.junit.Test
import org.mockito.Answers

class BaseListAdapterTest : BaseUnitTest() {

    @Test
    fun verifyBindItem() {
        val model = DummyModel()
        val adapter = mock<BaseListAdapter<DummyModel>>(defaultAnswer = Answers.CALLS_REAL_METHODS)
        val holder = mock<BaseViewHolder<DummyModel>>(defaultAnswer = Answers.CALLS_REAL_METHODS)

        adapter.bindItem(holder, model)

        verify(holder).bind(model)
    }
}
