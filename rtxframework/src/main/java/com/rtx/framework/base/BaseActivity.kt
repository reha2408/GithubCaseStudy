package com.rtx.framework.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun lockScreen()
    abstract fun unlockScreen()
}
