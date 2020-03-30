package com.example.bookshelf.di;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDaoModule.class,
        PresenterModule.class})

public interface AppComponent {
    ActivityComponent activityComponent();

    PresenterComponent presenterComponent();
}
