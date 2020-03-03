package com.example.bookshelf;

import android.view.View;

import com.example.bookshelf.activities.EditBookActivity;
import com.example.bookshelf.models.Item;
import com.example.bookshelf.room.Book;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private BookDatabase db = App.getInstance().getDatabase();
    private BookDao bookDao = db.bookDao();
    private Book bookDb;

    public void save(List<Item> bookResult) {
        List<Book> booksFromDatabase = search(bookResult);
        bookDao.insert(booksFromDatabase);
    }

    public List<Book> search(List<Item> bookResult) {
        List<Book> booksSearch = new ArrayList<>();
        for (int i = 0; i < bookResult.size(); i++) {
            Book book = new Book();
            if (bookResult.get(i).getVolumeInfo().getAuthors() == null) {
                book.setTitle("");
            } else {
                book.setAuthors(bookResult.get(i).getVolumeInfo().getAuthors().toString()
                        .replace("[", "")
                        .replace("]", ""));
            }
            if (bookResult.get(i).getVolumeInfo().getTitle() == null) {
                book.setTitle("");
            } else {
                book.setTitle(bookResult.get(i).getVolumeInfo().getTitle());
            }
            if (bookResult.get(i).getVolumeInfo().getImageLinks() != null
                    && bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail() != null) {
                book.setImageLinks(bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail());
            }
            if (bookResult.get(i).getVolumeInfo().getAverageRating() == 0) {
                book.setAverageRating(0);
            } else {
                book.setAverageRating(bookResult.get(i).getVolumeInfo().getAverageRating());
            }
            if (bookResult.get(i).getVolumeInfo().getPublisher() == null) {
                book.setPublisher("");
            } else {
                book.setPublisher(bookResult.get(i).getVolumeInfo().getPublisher());
            }
            if (bookResult.get(i).getVolumeInfo().getPublishedDate() == null) {
                book.setPublishedDate("");
            } else {
                book.setPublishedDate(bookResult.get(i).getVolumeInfo().getPublishedDate());
            }
            if (bookResult.get(i).getVolumeInfo().getPageCount() == 0) {
                book.setPageCount(0);
            } else {
                book.setPageCount(bookResult.get(i).getVolumeInfo().getPageCount());
            }
            if (bookResult.get(i).getVolumeInfo().getLanguage() == null) {
                book.setLanguage("");
            } else {
                book.setLanguage(bookResult.get(i).getVolumeInfo().getLanguage());
            }
            if (bookResult.get(i).getVolumeInfo().getDescription() == null) {
                book.setDescription("");
            } else {
                book.setDescription(bookResult.get(i).getVolumeInfo().getDescription());
            }
            booksSearch.add(book);
        }
        return booksSearch;
    }

    private Book searchBookDb(Book book) {
        String title = book.title = book.getTitle();
        String authors = book.authors = book.getAuthors();
        return bookDao.findBookTitleAndAuthor(title, authors);
    }

    public void hideOrDisplayButton(Book book) {
        bookDb = searchBookDb(book);
        if (bookDb != null) {
            EditBookActivity.delete.setVisibility(View.VISIBLE);
        } else {
            EditBookActivity.delete.setVisibility(View.GONE);
        }
    }

    public void insertOrUpdate(Book book) {
        bookDb = searchBookDb(book);
        if (bookDb != null) {
            bookDao.update(book);

        } else {
            bookDao.insert(book);
        }
    }

    public void delete(Book book) {
        bookDao.delete(book);
    }

    public List<Book> getList() {
        return bookDao.getList();
    }
}
