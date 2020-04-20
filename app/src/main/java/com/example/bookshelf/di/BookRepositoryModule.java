package com.example.bookshelf.di;

import com.example.bookshelf.features.bookssearch.BookRepository;
import com.example.bookshelf.features.bookssearch.Repository;
import com.example.bookshelf.network.GoogleBooksApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class BookRepositoryModule {
    @Provides
    public Repository providesBookStorage(GoogleBooksApiService service) {
        return new BookRepository(service);
    }
}
