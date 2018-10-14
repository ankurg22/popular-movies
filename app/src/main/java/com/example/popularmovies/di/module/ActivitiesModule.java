package com.example.popularmovies.di.module;

import com.example.popularmovies.app.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}
