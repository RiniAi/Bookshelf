package com.example.bookshelf.usecases;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.book.BookStorage;

import javax.inject.Inject;

public class SearchBookUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public SearchBookUseCase() {
    }

    public Book run(Params params) {
        return storage.search(params.getBook());
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
}
