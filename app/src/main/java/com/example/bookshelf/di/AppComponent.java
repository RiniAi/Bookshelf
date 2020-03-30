package com.example.bookshelf.di;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDaoModule.class,
        PresenterModule.class})

public interface AppComponent {
    ActivityComponent activityComponent();

    MainPresenterComponent mainPresenterComponent();

    SearchPresenterComponent searchPresenterComponent();

    EditBookPresenterComponent editBookPresenterComponent();

    BookChallengePresenterComponent bookChallengePresenterComponent();
}
