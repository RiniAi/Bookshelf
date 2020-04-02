package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.LocalBookStorage;

import java.util.List;

import javax.inject.Inject;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;

public class SearchStatusUseCase {
    @Inject
    LocalBookStorage storage;

    @Inject
    public SearchStatusUseCase() {
    }

    public List<Book> run() {
        return storage.getAllWithStatus(FINISH_READING);
    }
}
