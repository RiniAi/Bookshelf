package com.example.bookshelf.usecases;

import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.database.bookChallenge.BookChallengeStorage;

import javax.inject.Inject;

public class SearchBookChallengeUseCase {
    @Inject
    BookChallengeStorage storage;

    @Inject
    public SearchBookChallengeUseCase() {
    }

    public BookChallenge run(Params params) {
        return storage.search(params.getBookChallenge());
    }

    public static class Params {
        private BookChallenge bookChallenge;

        private BookChallenge getBookChallenge() {
            return bookChallenge;
        }

        private Params(BookChallenge bookChallenge) {
            this.bookChallenge = bookChallenge;
        }

        //Replacing the constructor with the factory method
        public static Params createParams (BookChallenge bookChallenge) {
            return new Params(bookChallenge);
        }
    }
}
