package com.example.bookshelf.database.book;

import java.util.List;

public interface BookStorage {
    Book search(Book book);

    List<Book> getAllWithStatus(Book.BookStatus status);

    void insertOrUpdate(Book book);

    void delete(Book book);
}
