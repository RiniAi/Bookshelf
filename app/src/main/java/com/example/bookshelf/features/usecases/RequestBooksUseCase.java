package com.example.bookshelf.features.usecases;

import com.example.bookshelf.features.bookssearch.BookRepository;
import com.example.bookshelf.features.bookssearch.SearchCall;

import javax.inject.Inject;

public class RequestBooksUseCase {
    @Inject
    BookRepository repository;

    @Inject
    public RequestBooksUseCase() {
    }

    public void run(String query, SearchCall.responseListener responseListener) {
        repository.requestBooksFromApi(query, responseListener);
    }
}
