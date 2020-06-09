package com.example.bookshelf.database.book;

import com.example.bookshelf.App;

import java.util.List;

import javax.inject.Inject;

public class LocalBookStorage implements BookStorage {
    @Inject
    BookDao bookDao;

    @Inject
    public LocalBookStorage() {
        App.getAppComponent().inject(this);
    }

    public Book search(Book book) {
        return bookDao.findBookTitleAndAuthor(book.getTitle(), book.getAuthors());
    }

    public List<Book> getAllWithStatus(Book.BookStatus status) {
        return bookDao.getAllWithStatus(BookStatusConverter.fromStatusToString(status));
    }

    public void insertOrUpdate(Book book) {
        if (search(book) != null) {
            bookDao.update(book);
        } else {
            bookDao.insert(book);
        }
    }

    public void delete(Book book) {
        bookDao.delete(book);
    }
}
