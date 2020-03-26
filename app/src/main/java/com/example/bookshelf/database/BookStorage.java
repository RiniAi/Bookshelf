package com.example.bookshelf.database;

import java.util.List;

import javax.inject.Inject;

public class BookStorage {
    @Inject
    BookDao bookDao;
    @Inject
    public BookStorage() {
    }

    public Book searchBookDb(Book book) {
        return bookDao.findBookTitleAndAuthor(book.title, book.authors);
    }

    public List<Book> getAllWithStatus(Book.BookStatus status) {
        return bookDao.getAllWithStatus(BookStatusConverter.fromStatusToString(status));
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public void insertOrUpdate(Book book) {
        if (searchBookDb(book) != null) {
            bookDao.update(book);
        } else {
            bookDao.insert(book);
        }
    }

    public void delete(Book book) {
        bookDao.delete(book);
    }
}
