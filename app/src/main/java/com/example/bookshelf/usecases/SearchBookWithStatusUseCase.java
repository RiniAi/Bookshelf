package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

import javax.inject.Inject;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;

public class SearchBookWithStatusUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public SearchBookWithStatusUseCase() {
    }

    public SearchBookWithStatusUseCase(BookStorage storage) {
        this.storage = storage;
    }

    public List<Book> run() {
        return storage.getAllWithStatus(FINISH_READING);
    }
}
