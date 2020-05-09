package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

import javax.inject.Inject;

public class SearchBookWithStatusUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public SearchBookWithStatusUseCase() {
    }

    public List<Book> run(Book.BookStatus status) {
        return storage.getAllWithStatus(status);
    }
}
