package com.example.bookshelf.database;

import com.example.bookshelf.App;
import com.example.bookshelf.models.BooksApiResponseItem;

import java.util.ArrayList;
import java.util.List;

public class BookStorage {
    private BookDatabase db = App.getInstance().getDatabase();
    private BookDao bookDao = db.bookDao();

    public List<Book> mapResponseDomain(List<BooksApiResponseItem> bookResult) {
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

    public Book searchBookDb(Book book) {
        return bookDao.findBookTitleAndAuthor(book.title, book.authors);
    }

    public List<Book> searchForReadBooks() {
        return bookDao.getBookStatusReading(BookStatusConverter.fromStatusToString(Book.BookStatus.FINISH_READING));
    }

    public List<Book> searchForBooksWithStatus() {
        return bookDao.getBookStatus(
                BookStatusConverter.fromStatusToString(Book.BookStatus.IN_THE_PROCESS_OF_READING),
                BookStatusConverter.fromStatusToString(Book.BookStatus.PLAN_READING),
                BookStatusConverter.fromStatusToString(Book.BookStatus.FINISH_READING),
                BookStatusConverter.fromStatusToString(Book.BookStatus.QUIT_READING));
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

    public List<Book> getList() {
        return bookDao.getList();
    }
}
