package com.reha.casestudy.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.reha.casestudy.BuildConfig
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val dateFormat = "yyyy.MM.dd hh:mm:ss"

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("case_study_app", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat(dateFormat)
            .create()
    }

    @Provides
    @Singleton
    fun providePicasso(app: Application?): Picasso {
        return Picasso.Builder(app)
            .listener { picasso, uri, exception ->
                if (BuildConfig.DEBUG) {
                    exception.printStackTrace()
                }
            }
            .build()
    }
}
