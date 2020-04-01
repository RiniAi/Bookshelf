package com.example.bookshelf.features.bookssearch;

public interface Repository {
    void requestBooksFromApi(String query, SearchCall.responseListener responseListener);
}
