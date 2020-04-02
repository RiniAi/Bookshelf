package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.LocalBookStorage;

import java.util.List;

import javax.inject.Inject;

public class LoadUseCase {
    @Inject
    LocalBookStorage storage;

    @Inject
    public LoadUseCase() {
    }

    public List<Book> run() {
        return storage.getAll();
    }
}
