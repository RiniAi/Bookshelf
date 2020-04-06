package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.LocalBookStorage;

import javax.inject.Inject;

public class SearchBookUseCase {
    @Inject
    LocalBookStorage storage;

    @Inject
    public SearchBookUseCase() {
    }

    public static class Params {
        private Book book;

        public Book getBook() {
            return book;
        }

        public Params(Book book) {
            this.book = book;
        }
    }

    public Book run(Params params) {
        return storage.search(params.getBook());
    }
}
