package com.reha.casestudy.di.network;

import com.google.gson.Gson;
import com.reha.casestudy.feature.github.data.GitHubApiService;
import com.reha.casestudy.base.SearchConverterFactory;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return HttpUrl.parse("https://api.github.com/");
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client,
                             Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(new SearchConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    GitHubApiService provideGithubApiService(Retrofit retrofit) {
        return retrofit.create(GitHubApiService.class);
    }
}
