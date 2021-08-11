package com.reha.casestudy.di.base;

import android.app.Application;

import com.reha.casestudy.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return app;
    }
}