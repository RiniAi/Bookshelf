package com.example.bookshelf.network;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.models.BooksApiResponse;
import com.example.bookshelf.models.BooksApiResponseItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BookMapper {

    @Inject
    public BookMapper() {
    }

    public List<Book> mapResponseToDomain(BooksApiResponse bookResult) {
        List<Book> booksSearch = new ArrayList<>();
        for (BooksApiResponseItem item : bookResult.getItems()) {
            Book book = new Book();
            if (item.getVolumeInfo().getAuthors() == null) {
                book.setAuthors("");
            } else {
                book.setAuthors(item.getVolumeInfo().getAuthors().toString()
                        .replace("[", "")
                        .replace("]", ""));
            }
            book.setTitle(item.getVolumeInfo().getTitle());
            if (item.getVolumeInfo().getImageLinks() != null
                    && item.getVolumeInfo().getImageLinks().getThumbnail() != null) {
                book.setImageLinks(item.getVolumeInfo().getImageLinks().getThumbnail());
            }
            book.setAverageRating(item.getVolumeInfo().getAverageRating());
            book.setPublisher(item.getVolumeInfo().getPublisher());
            book.setPublishedDate(item.getVolumeInfo().getPublishedDate());
            book.setPageCount(item.getVolumeInfo().getPageCount());
            book.setLanguage(item.getVolumeInfo().getLanguage());
            book.setDescription(item.getVolumeInfo().getDescription());

            booksSearch.add(book);
        }
        return booksSearch;
    }
}
