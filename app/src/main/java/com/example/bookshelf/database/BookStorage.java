package com.example.bookshelf.database;

import java.util.List;

public interface BookStorage {
    Book searchBookDb(Book book);

    List<Book> getAllWithStatus(Book.BookStatus status);

    List<Book> getAll();

    void insertOrUpdate(Book book);

    void delete(Book book);
}
