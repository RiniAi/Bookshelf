package com.example.bookshelf.usecases;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.book.BookStorage;

import javax.inject.Inject;

public class DeleteBookUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public DeleteBookUseCase() {
    }

    public void run(Params params) {
        storage.delete(params.getBook());
    }

    public static class Params {
        private Book book;

        private Book getBook() {
            return book;
        }

        private Params(Book book) {
            this.book = book;
        }

        //Replacing the constructor with the factory method
        public static Params createParams(Book book) {
            return new Params(book);
        }
    }
}
