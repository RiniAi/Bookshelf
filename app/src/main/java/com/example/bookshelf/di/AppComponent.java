package com.example.bookshelf.di;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDataBaseModule.class,
        PresenterModule.class})

public interface AppComponent {
    ActivityComponent activityComponent();

    PresenterComponent presenterComponent();
}
