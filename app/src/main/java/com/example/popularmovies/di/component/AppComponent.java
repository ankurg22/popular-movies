package com.example.popularmovies.di.component;

import android.app.Application;

import com.example.popularmovies.App;
import com.example.popularmovies.di.module.AppModule;
import com.example.popularmovies.di.module.ActivitiesModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ActivitiesModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
