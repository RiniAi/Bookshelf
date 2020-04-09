package com.example.bookshelf.usecases;

import com.example.bookshelf.features.bookssearch.Repository;
import com.example.bookshelf.features.bookssearch.SearchCall;

import javax.inject.Inject;

public class RequestBooksUseCase {
    @Inject
    Repository repository;

    @Inject
    public RequestBooksUseCase() {
    }

    public RequestBooksUseCase(Repository repository) {
        this.repository = repository;
    }

    public void run(String query, SearchCall.ResponseListener responseListener) {
        repository.requestBooksFromApi(query, responseListener);
    }
}
