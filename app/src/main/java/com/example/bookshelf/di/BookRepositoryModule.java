package com.example.bookshelf.di;

import com.example.bookshelf.features.bookssearch.BookRepository;
import com.example.bookshelf.features.bookssearch.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class BookRepositoryModule {
    @Provides
    public Repository providesBookStorage () {
        return new BookRepository();
    }
}
