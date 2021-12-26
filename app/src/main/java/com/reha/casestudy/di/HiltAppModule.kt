package com.reha.casestudy.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.reha.casestudy.BuildConfig
import com.reha.casestudy.feature.github.data.GitHubApiService
import com.reha.casestudy.feature.github.data.repo.GitHubDataRepository
import com.reha.casestudy.feature.github.data.repo.datasource.GithubRemoteDataSource
import com.reha.casestudy.feature.github.domain.GitHubRepository
import com.reha.casestudy.network.SearchConverterFactory
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltAppModule {

    @Provides
    @Singleton
    fun provideGithubRepository(remoteDataSource: GithubRemoteDataSource): GitHubRepository =
        GitHubDataRepository(remoteDataSource)

    @Provides
    @Singleton
    fun provideGithubRemoteDataSource(service: GitHubApiService): GithubRemoteDataSource =
        GithubRemoteDataSource(service)
}

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): HttpUrl? {
        return "https://api.github.com/".toHttpUrlOrNull()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: HttpUrl?, client: OkHttpClient?, gson: Gson?): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(SearchConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    val dateFormat = "yyyy.MM.dd hh:mm:ss"

    fun createOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

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

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        val builder = createOkHttpClient()
            .addInterceptor(httpLoggingInterceptor as Interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        return builder.build()
    }
}
