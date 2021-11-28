package com.rtx.framework.model

import androidx.annotation.StringRes

data class UiMessage(
    val messageText: String? = null,
    @StringRes val messageId: Int? = null
) {

    fun type() = if (!messageText.isNullOrEmpty()) {
        Type.TEXT
    } else if (messageId != null) {
        Type.ID
    } else {
        Type.NONE
    }

    enum class Type {
        TEXT,
        ID,
        NONE
    }
}
