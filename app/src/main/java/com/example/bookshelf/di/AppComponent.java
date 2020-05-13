package com.example.bookshelf.di;

import com.example.bookshelf.database.LocalBookChallengeStorage;
import com.example.bookshelf.database.LocalBookStorage;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        ServiceModule.class,
        BookDataBaseModule.class,
        PresenterModule.class,
        BookStorageModule.class,
        BookChallengeStorageModule.class,
        BookRepositoryModule.class})

public interface AppComponent {
    ActivityComponent activityComponent();

    void inject(LocalBookStorage storage);

    void inject(LocalBookChallengeStorage storage);
}
