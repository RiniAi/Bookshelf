package com.example.bookshelf.usecases;

import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.database.bookChallenge.BookChallengeStorage;

import java.util.Objects;

import javax.inject.Inject;

public class InsertOrUpdateBookChallengeUseCase {
    @Inject
    BookChallengeStorage storage;

    @Inject
    public InsertOrUpdateBookChallengeUseCase() {
    }

    public void run(Params params) {
        storage.insertOrUpdate(params.getBookChallenge());
    }

    public static class Params {
        private BookChallenge bookChallenge;

        public BookChallenge getBookChallenge() {
            return bookChallenge;
        }

        public Params(BookChallenge bookChallenge, int progress, int counter) {
            bookChallenge.progress = progress;
            bookChallenge.counter = counter;
            this.bookChallenge = bookChallenge;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return Objects.equals(bookChallenge, params.bookChallenge);
        }

        @Override
        public int hashCode() {
            return bookChallenge != null ? bookChallenge.hashCode() : 0;
        }
    }
}
