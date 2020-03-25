package com.example.bookshelf.database;

import com.example.bookshelf.App;

import java.util.List;

import javax.inject.Inject;

public class BookStorage {

    @Inject
    BookDao bookDao;

    public void onStart() {
        App.getAppComponent().injectBookStorage(this);
    }

    public Book searchBookDb(Book book) {
        onStart();
        return bookDao.findBookTitleAndAuthor(book.title, book.authors);
    }

    public List<Book> getAllWithStatus(Book.BookStatus status) {
        onStart();
        return bookDao.getAllWithStatus(BookStatusConverter.fromStatusToString(status));
    }

    public List<Book> getAll() {
        onStart();
        return bookDao.getAll();
    }

    public void insertOrUpdate(Book book) {
        onStart();
        if (searchBookDb(book) != null) {
            bookDao.update(book);
        } else {
            bookDao.insert(book);
        }
    }

    public void delete(Book book) {
        onStart();
        bookDao.delete(book);
    }
}
