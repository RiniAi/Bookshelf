package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

import javax.inject.Inject;

public class LoadBookUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public LoadBookUseCase() {
    }

    public List<Book> run() {
        return storage.getAll();
    }
}
