package com.example.bookshelf.di;

import com.example.bookshelf.database.BookChallengeStorage;
import com.example.bookshelf.database.LocalBookChallengeStorage;

import dagger.Module;
import dagger.Provides;

@Module
public class BookChallengeStorageModule {
    @Provides
    public BookChallengeStorage providesBookChallengeStorage () {
        return new LocalBookChallengeStorage();
    }
}
