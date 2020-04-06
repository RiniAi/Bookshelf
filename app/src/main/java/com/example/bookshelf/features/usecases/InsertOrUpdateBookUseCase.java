package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.LocalBookStorage;

import javax.inject.Inject;

public class InsertOrUpdateBookUseCase {
    @Inject
    LocalBookStorage storage;

    @Inject
    public InsertOrUpdateBookUseCase() {
    }

    public static class Params {
        private Book book;

        public Book getBook() {
            return book;
        }

        public Params(Book book, float rating, String status, boolean isFavorite, String date) {
            book.userRating = rating;
            book.isFavorite = isFavorite;
            book.setStatus(BookStatusConverter.fromStringToStatus(status));
            if (book.isFinishedOrQuit()) {
                book.readDate = date;
            } else {
                book.readDate = "";
            }
            this.book = book;
        }
    }

    public void run(Params params) {
        storage.insertOrUpdate(params.getBook());
    }
}
