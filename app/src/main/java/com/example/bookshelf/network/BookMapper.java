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
}
