package com.rtx.framework.model

import com.rtx.framework.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UiMessageTest {

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
