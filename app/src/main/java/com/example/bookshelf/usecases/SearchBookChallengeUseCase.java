package com.example.bookshelf.usecases;

import com.example.bookshelf.database.BookChallenge;
import com.example.bookshelf.database.BookChallengeStorage;

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

        BookChallenge getBookChallenge() {
            return bookChallenge;
        }

        public Params(BookChallenge bookChallenge) {
            this.bookChallenge = bookChallenge;
        }
    }
}
