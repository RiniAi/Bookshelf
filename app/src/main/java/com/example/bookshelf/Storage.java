package com.example.bookshelf;

import com.example.bookshelf.App;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;
import com.example.bookshelf.room.BookEntity;

import java.util.List;

public class Storage {
    private BookDatabase db = App.getInstance().getDatabase();
    private BookDao bookDao = db.bookDao();

    public BookEntity converting(BookEntity bookEntity, Book book) {
        bookEntity.authors = book.getAuthors();
        bookEntity.title = book.getTitle();
        bookEntity.imageLinks = book.getImageURL();
        bookEntity.averageRating = book.getAverageRating();
        bookEntity.description = book.getDescription();
        return bookEntity;
    }

    public void insert(List<BookEntity> list) {
        bookDao.insert(list);
    }

    public void update(BookEntity bookEntity) {
        bookDao.update(bookEntity);
    }
}
