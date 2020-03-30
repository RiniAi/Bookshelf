package com.example.bookshelf.di;

import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;

import dagger.Subcomponent;

@Subcomponent
public interface BookChallengePresenterComponent {
    void inject(BookChallengePresenter presenter);
}
