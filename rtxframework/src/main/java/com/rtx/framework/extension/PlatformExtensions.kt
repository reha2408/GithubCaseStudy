package com.rtx.framework.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rtx.framework.model.UiMessage

fun Fragment?.showError(text: String?) = this?.context?.let {
    Toast.makeText(
        it,
        text,
        Toast.LENGTH_SHORT
    ).show()
}

fun Fragment?.showError(strId: Int?) = this?.context?.let { context ->
    strId?.let {
        Toast.makeText(
            context,
            strId,
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun Fragment?.showError(message: UiMessage?) = message?.let {
    when (it.type()) {
        UiMessage.Type.TEXT -> showError(it.messageText)
        UiMessage.Type.ID -> showError(it.messageId)
        else -> {
        }
    }
}