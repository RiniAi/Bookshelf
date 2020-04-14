package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.BookStorage;

import javax.inject.Inject;

public class InsertOrUpdateBookUseCase {
    @Inject
    BookStorage storage;

    @Inject
    public InsertOrUpdateBookUseCase() {
    }

    public void run(Params params) {
        storage.insertOrUpdate(params.getBook());
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return book.equals(params.book);
        }

        @Override
        public int hashCode() {
            return book.hashCode();
        }
    }
}
