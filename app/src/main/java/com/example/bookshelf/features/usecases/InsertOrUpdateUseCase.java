package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.LocalBookStorage;

import javax.inject.Inject;

public class InsertOrUpdateUseCase {
    @Inject
    LocalBookStorage storage;

    @Inject
    public InsertOrUpdateUseCase() {
    }

    public static class Params<T> {
        private T book;

        public T getBook() {
            return book;
        }

        public Params(T book, float rating, String status, boolean isFavorite, String date) {
            ((Book) book).userRating = rating;
            ((Book) book).isFavorite = isFavorite;
            ((Book) book).setStatus(BookStatusConverter.fromStringToStatus(status));
            if (((Book) book).isFinishedOrQuit()) {
                ((Book) book).readDate = date;
            } else {
                ((Book) book).readDate = "";
            }
            this.book = book;
        }
    }

    public void run(Params params) {
        storage.insertOrUpdate((Book) params.getBook());
    }
}
