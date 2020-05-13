package com.example.bookshelf.database;

import com.example.bookshelf.App;

import java.util.List;

import javax.inject.Inject;

public class LocalBookChallengeStorage implements BookChallengeStorage {
    @Inject
    BookChallengeDao bookChallengeDao;

    @Inject
    public LocalBookChallengeStorage() {
        App.getAppComponent().inject(this);
    }

    public List<BookChallenge> getAll() {
        return bookChallengeDao.getAll();
    }

    public void insertOrUpdate(BookChallenge bookChallenge) {
        if (search(bookChallenge) != null) {
            bookChallengeDao.update(bookChallenge);
        } else {
            bookChallengeDao.insert(bookChallenge);
        }
    }

    public BookChallenge search(BookChallenge bookChallenge) {
        return bookChallengeDao.findBookChallengeYear(bookChallenge.year);
    }
}
