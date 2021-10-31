package com.rtx.framework.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

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