package com.rtx.framework.extension

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rtx.framework.R
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

fun FragmentTransaction.addSlideAnimation(): FragmentTransaction =
    setCustomAnimations(
        android.R.anim.slide_in_left,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.slide_out_right
    )

fun FragmentTransaction.addFadeAnimation(): FragmentTransaction =
    setCustomAnimations(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )

fun FragmentTransaction.addDelayAnimation(): FragmentTransaction =
    setCustomAnimations(
        R.anim.delay_anim,
        R.anim.delay_anim,
        R.anim.delay_anim,
        R.anim.delay_anim
    )

fun <T : Fragment> T.withArgs(key: String?, value: Parcelable?): T {
    this.arguments = Bundle().apply {
        putParcelable(key, value)
    }
    return this
}

inline fun <reified T : Fragment> newInstance(vararg params: Pair<String, Any>): T =
    newInstance<T>().apply {
        arguments = bundleOf(*params)
    }

inline fun <reified T : Fragment> newInstance(): T = T::class.java.newInstance()
