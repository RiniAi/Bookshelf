package com.example.bookshelf.di;

import com.example.bookshelf.database.book.BookStorage;
import com.example.bookshelf.database.book.LocalBookStorage;

import dagger.Module;
import dagger.Provides;

@Module
public class BookStorageModule {
    @Provides
    public BookStorage providesBookStorage () {
        return new LocalBookStorage();
    }
}
