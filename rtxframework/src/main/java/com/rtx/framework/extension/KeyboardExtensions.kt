package com.rtx.framework.extension

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

internal fun isLegacyModeActive() = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP

fun View.showSoftKeyboard() = post {
    takeIf { requestFocus() }?.run {
        if (isLegacyModeActive()) {
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.showSoftInput(this,InputMethodManager.SHOW_FORCED)
        } else {
            ViewCompat.getWindowInsetsController(this)?.show(WindowInsetsCompat.Type.ime())
        }
    }
}

fun View.hideSoftKeyboard(clearFocus: Boolean = false) = post {
    if (clearFocus) clearFocus()
    if (isLegacyModeActive()) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(windowToken, 0)
    } else {
        ViewCompat.getWindowInsetsController(this)?.hide(WindowInsetsCompat.Type.ime())
    }
}

private val Activity.focusedView get() = currentFocus ?: window.decorView

private val Fragment.focusedView get() = activity?.currentFocus ?: view

fun Activity.showSoftKeyboard() = focusedView.showSoftKeyboard()
fun Activity.hideSoftKeyboard(clearFocus: Boolean = false) = focusedView.hideSoftKeyboard(clearFocus)

fun Fragment.showSoftKeyboard() = focusedView?.showSoftKeyboard()
fun Fragment.hideSoftKeyboard(clearFocus: Boolean = false) = focusedView?.hideSoftKeyboard(clearFocus)