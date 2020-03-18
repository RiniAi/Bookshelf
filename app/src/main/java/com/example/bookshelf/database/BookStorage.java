package com.example.bookshelf.database;

import com.example.bookshelf.App;

import java.util.List;

public class BookStorage {
    private BookDatabase db = App.getInstance().getDatabase();
    private BookDao bookDao = db.bookDao();

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
        Book bookDb = searchBookDb(book);
        if (bookDb != null) {
            bookDao.update(book);
        } else {
            bookDao.insert(book);
        }
    }

    public void delete(Book book) {
        bookDao.delete(book);
    }
}
