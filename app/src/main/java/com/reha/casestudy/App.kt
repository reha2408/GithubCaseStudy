package com.reha.casestudy

import android.app.Application
import com.reha.casestudy.di.base.AppComponent
import com.reha.casestudy.di.base.AppModule
import com.reha.casestudy.di.base.DaggerAppComponent

class App: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupGraph()
    }

    fun setupGraph() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

    fun component() = appComponent
}