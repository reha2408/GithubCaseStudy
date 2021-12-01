package com.reha.casestudy.extension

import android.content.Context
import com.reha.casestudy.R

class MyUtil(val context: Context) {

    fun getHelloWorldString() = context.getString(R.string.hello_world)
}