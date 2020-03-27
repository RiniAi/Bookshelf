package com.example.bookshelf.di.bookchallenge;

import com.example.bookshelf.features.bookchallenge.BookChallengeContract;
import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class BookChallengePresenterModule {
    @Provides
    public BookChallengeContract.Presenter providesPresenter() {
        return new BookChallengePresenter();
    }
}
