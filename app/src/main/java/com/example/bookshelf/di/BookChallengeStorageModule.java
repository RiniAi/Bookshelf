package com.example.bookshelf.di;

import com.example.bookshelf.database.bookChallenge.BookChallengeStorage;
import com.example.bookshelf.database.bookChallenge.LocalBookChallengeStorage;

import dagger.Module;
import dagger.Provides;

@Module
public class BookChallengeStorageModule {
    @Provides
    public BookChallengeStorage providesBookChallengeStorage () {
        return new LocalBookChallengeStorage();
    }
}
