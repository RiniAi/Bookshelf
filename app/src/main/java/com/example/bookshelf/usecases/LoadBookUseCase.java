package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

import javax.inject.Inject;

public class LoadBookUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public LoadBookUseCase(BookStorage storage) {
        this.storage = storage;
    }

    public List<Book> run() {
        return storage.getAll();
    }
}
