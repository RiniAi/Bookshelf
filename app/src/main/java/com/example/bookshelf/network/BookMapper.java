package com.example.bookshelf.network;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.models.BooksApiResponseItem;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static List<Book> mapResponseToDomain(List<BooksApiResponseItem> bookResult) {
        List<Book> booksSearch = new ArrayList<>();
        for (int i = 0; i < bookResult.size(); i++) {
            Book book = new Book();
            if (bookResult.get(i).getVolumeInfo().getAuthors() == null) {
                book.setAuthors("");
            } else {
                book.setAuthors(bookResult.get(i).getVolumeInfo().getAuthors().toString()
                        .replace("[", "")
                        .replace("]", ""));
            }
            book.setTitle(bookResult.get(i).getVolumeInfo().getTitle());
            if (bookResult.get(i).getVolumeInfo().getImageLinks() != null
                    && bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail() != null) {
                book.setImageLinks(bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail());
            }
            book.setAverageRating(bookResult.get(i).getVolumeInfo().getAverageRating());
            book.setPublisher(bookResult.get(i).getVolumeInfo().getPublisher());
            book.setPublishedDate(bookResult.get(i).getVolumeInfo().getPublishedDate());
            book.setPageCount(bookResult.get(i).getVolumeInfo().getPageCount());
            book.setLanguage(bookResult.get(i).getVolumeInfo().getLanguage());
            book.setDescription(bookResult.get(i).getVolumeInfo().getDescription());

            booksSearch.add(book);
        }
        return booksSearch;
    }
}
