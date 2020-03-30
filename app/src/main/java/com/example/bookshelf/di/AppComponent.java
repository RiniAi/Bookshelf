package com.example.bookshelf.di;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDaoModule.class,
        MainPresenterModule.class,
        SearchPresenterModule.class,
        EditBookPresenterModule.class,
        AboutBookPresenterModule.class,
        BookChallengePresenterModule.class})

public interface AppComponent {
    ActivityComponent activityComponent();

    MainPresenterComponent mainPresenterComponent();

    SearchPresenterComponent searchPresenterComponent();

    EditBookPresenterComponent editBookPresenterComponent();

    BookChallengePresenterComponent bookChallengePresenterComponent();
}
