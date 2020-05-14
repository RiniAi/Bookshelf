package com.example.bookshelf.database.bookChallenge;

import java.util.List;

public interface BookChallengeStorage {
    List<BookChallenge> getAll();

    BookChallenge search(BookChallenge bookChallenge);

    void insertOrUpdate(BookChallenge bookChallenge);
}
