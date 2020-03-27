package com.example.bookshelf.di.bookchallenge;

import com.example.bookshelf.features.bookchallenge.BookChallengeActivity;

import dagger.Subcomponent;

@Subcomponent
public interface BookChallengeActivityComponent {
    void inject(BookChallengeActivity activity);
}
