package com.rtx.framework.model

import com.rtx.framework.R
import com.rtx.framework.base.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class UiMessageTest : BaseUnitTest() {

    @Test
    fun `get type 'Text' when messageText is set`() {
        // given
        val uiMessage = UiMessage(messageText = "Message")
        // when
        val type = uiMessage.type()
        // then
        assertEquals(type, UiMessage.Type.TEXT)
        assertNotNull(uiMessage.messageText)
    }

    @Test
    fun `get type 'Id' when messageId is set`() {
        // given
        val uiMessage = UiMessage(messageId = R.string.test_text)
        // when
        val type = uiMessage.type()
        // then
        assertEquals(type, UiMessage.Type.ID)
        assertNotNull(uiMessage.messageId)
    }

    @Test
    fun `get type 'None' when nothing is set`() {
        // given
        val uiMessage = UiMessage()
        // when
        val type = uiMessage.type()
        // then
        assertEquals(type, UiMessage.Type.NONE)
        assertNull(uiMessage.messageText)
        assertNull(uiMessage.messageId)
    }
}
