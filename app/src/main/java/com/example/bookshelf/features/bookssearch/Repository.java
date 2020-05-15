package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.book.Book;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface Repository {
    Single<List<Book>> getBooks(String query, int startIndex);
}
