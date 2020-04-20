package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.network.GoogleBooksApiService;
import com.example.bookshelf.network.RetrofitClientInstance;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

import static com.example.bookshelf.network.BookMapper.mapResponseToDomain;
import static com.example.bookshelf.network.GoogleBooksApiService.QUERY_COUNTER;

public class BookRepository implements Repository {
    @Inject
    public BookRepository() {
    }
    public Single<List<Book>> getBooks(String query) {
        GoogleBooksApiService service = RetrofitClientInstance.getRetrofitInstance().create(GoogleBooksApiService.class);
        return service.getBooks(query, QUERY_COUNTER)
                .map(booksApiResponse -> mapResponseToDomain(booksApiResponse));
    }
}
