package com.rtx.framework.util

import android.content.Context
import com.rtx.framework.R


class FwUtil(val context: Context) {

    fun getHelloWorldString() = context.getString(R.string.hello_world)
}