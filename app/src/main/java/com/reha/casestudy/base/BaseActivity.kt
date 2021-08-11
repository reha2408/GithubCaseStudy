package com.reha.casestudy.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun lockScreen()
    abstract fun unlockScreen()
}