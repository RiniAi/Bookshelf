package com.example.bookshelf.features.bookchallenge;

import dagger.Module;
import dagger.Provides;

@Module
public class BookChallengePresenterModule {
    @Provides
    public BookChallengeContract.Presenter providesPresenter() {
        return new BookChallengePresenter();
    }
}
