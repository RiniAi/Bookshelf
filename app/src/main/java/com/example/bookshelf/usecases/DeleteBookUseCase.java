package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import javax.inject.Inject;

public class DeleteBookUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public DeleteBookUseCase() {
    }

    public DeleteBookUseCase(BookStorage storage) {
        this.storage = storage;
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

    public void run(Params params) {
        storage.delete(params.getBook());
    }
}
