package com.example.bookshelf;

import com.example.bookshelf.models.Item;
import com.example.bookshelf.room.Book;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private BookDatabase db = App.getInstance().getDatabase();
    private BookDao bookDao = db.bookDao();

    public void save(List<Item> bookResult) {
        List<Book> booksFromDatabase = new ArrayList<>();
        for (int i = 0; i < bookResult.size(); i++) {
            Book book = new Book();
            book.setAuthors(bookResult.get(i).getVolumeInfo().getAuthors().toString()
                    .replace("[", "")
                    .replace("]", ""));
            book.setTitle(bookResult.get(i).getVolumeInfo().getTitle());
            book.setImageLinks(bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail());
            book.setAverageRating(bookResult.get(i).getVolumeInfo().getAverageRating());
            book.setPublisher(bookResult.get(i).getVolumeInfo().getPublisher());
            // TODO anna 28.02.2020: make a Date and convert to String
            book.setPublishedDate(bookResult.get(i).getVolumeInfo().getPublishedDate());
            book.setPageCount(bookResult.get(i).getVolumeInfo().getPageCount());
            book.setLanguage(bookResult.get(i).getVolumeInfo().getLanguage());
            book.setDescription(bookResult.get(i).getVolumeInfo().getDescription());
            booksFromDatabase.add(book);
        }
        bookDao.insert(booksFromDatabase);
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public void delete(Book book) {
        bookDao.delete(book);
    }

    public List<Book> getList() {
        List<Book> list = bookDao.getList();
        return list;
    }
}
