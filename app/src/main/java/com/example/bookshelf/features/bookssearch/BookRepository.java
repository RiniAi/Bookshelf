package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.network.GoogleBooksApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

import static com.example.bookshelf.network.BookMapper.mapResponseToDomain;
import static com.example.bookshelf.network.GoogleBooksApiService.QUERY_COUNTER;

public class BookRepository implements Repository {
    @Inject
    GoogleBooksApiService service;

    @Inject
    public BookRepository(GoogleBooksApiService service) {
        this.service = service;
    }

    public Single<List<Book>> getBooks(String query) {
        return service.getBooks(query, QUERY_COUNTER)
                .map(booksApiResponse -> mapResponseToDomain(booksApiResponse));
    }
}
