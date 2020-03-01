package com.example.bookshelf;

import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.Item;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;
import com.example.bookshelf.room.BookEntity;

import java.util.List;

public class Storage {
    private BookDatabase db = App.getInstance().getDatabase();
    private BookDao bookDao = db.bookDao();

    public BookEntity convertingItemToEntity(int i, List<Item> bookResult, List<Book> bookList) {
        Book book = new Book();
        book.setAuthors(bookResult.get(i).getVolumeInfo().getAuthors().toString()
                .replace("[", "")
                .replace("]", ""));
        book.setTitle(bookResult.get(i).getVolumeInfo().getTitle());
        book.setImageURL(bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail());
        book.setAverageRating(bookResult.get(i).getVolumeInfo().getAverageRating());
        book.setPublisher(bookResult.get(i).getVolumeInfo().getPublisher());
        // TODO anna 28.02.2020: make a Date and convert to String
        book.setPublishedDate(bookResult.get(i).getVolumeInfo().getPublishedDate());
        book.setPageCount(bookResult.get(i).getVolumeInfo().getPageCount());
        book.setLang(bookResult.get(i).getVolumeInfo().getLanguage());
        book.setDescription(bookResult.get(i).getVolumeInfo().getDescription());
        bookList.add(book);

        BookEntity bookEntity = new BookEntity();
        convertingBookToEntity(bookEntity, book);
        return bookEntity;
    }

    public BookEntity convertingBookToEntity(BookEntity bookEntity, Book book) {
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

    public void delete(BookEntity bookEntity) {
        bookDao.delete(bookEntity);
    }

    public Book loadBooks(Book book, BookEntity bookEntity) {
        book.setTitle(bookEntity.getTitle());
        book.setAuthors(bookEntity.getAuthors());
        book.setAverageRating(bookEntity.getAverageRating());
        book.setImageURL(bookEntity.getImageLinks());
        return book;
    }

    public List<BookEntity> getList() {
        List<BookEntity> list = bookDao.getList();
        return list;
    }
}
