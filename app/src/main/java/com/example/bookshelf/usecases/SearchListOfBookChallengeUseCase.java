package com.example.bookshelf.usecases;

import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.database.bookChallenge.BookChallengeStorage;

import java.util.List;

import javax.inject.Inject;

public class SearchListOfBookChallengeUseCase {
    @Inject
    BookChallengeStorage storage;

    @Inject
    public SearchListOfBookChallengeUseCase() {
    }

    public List<BookChallenge> run() {
        return storage.getAll();
    }
}
