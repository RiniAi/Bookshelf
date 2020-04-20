package com.example.bookshelf.network;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.models.BooksApiResponse;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static List<Book> mapResponseToDomain(BooksApiResponse bookResult) {
        List<Book> booksSearch = new ArrayList<>();
        for (int i = 0; i < bookResult.getItems().size(); i++) {
            Book book = new Book();
            if (bookResult.getItems().get(i).getVolumeInfo().getAuthors() == null) {
                book.setAuthors("");
            } else {
                book.setAuthors(bookResult.getItems().get(i).getVolumeInfo().getAuthors().toString()
                        .replace("[", "")
                        .replace("]", ""));
            }
            book.setTitle(bookResult.getItems().get(i).getVolumeInfo().getTitle());
            if (bookResult.getItems().get(i).getVolumeInfo().getImageLinks() != null
                    && bookResult.getItems().get(i).getVolumeInfo().getImageLinks().getThumbnail() != null) {
                book.setImageLinks(bookResult.getItems().get(i).getVolumeInfo().getImageLinks().getThumbnail());
            }
            book.setAverageRating(bookResult.getItems().get(i).getVolumeInfo().getAverageRating());
            book.setPublisher(bookResult.getItems().get(i).getVolumeInfo().getPublisher());
            book.setPublishedDate(bookResult.getItems().get(i).getVolumeInfo().getPublishedDate());
            book.setPageCount(bookResult.getItems().get(i).getVolumeInfo().getPageCount());
            book.setLanguage(bookResult.getItems().get(i).getVolumeInfo().getLanguage());
            book.setDescription(bookResult.getItems().get(i).getVolumeInfo().getDescription());

            booksSearch.add(book);
        }
        return booksSearch;
    }
}
