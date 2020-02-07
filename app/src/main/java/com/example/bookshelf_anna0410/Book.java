package com.example.bookshelf_anna0410;

import java.util.Date;

public class Book {
    private String title, authors, image, publisher, language, description, status;
    private int publishedDate, pageCount, averageRating, userRating;
    private Date date;
    private boolean favorite;


    // Constructor for About book
    public Book(String title, String authors, String image, int averageRating, int userRating, String publisher, int publishedDate, String language,  int pageCount, String description) {
        this.title = title;
        this.authors = authors;
        this.image = image;
        this.averageRating = averageRating;
        this.userRating = userRating;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.language = language;
        this.pageCount = pageCount;
        this.description = description;
    }

    // Constructor for List of books and Search
    public Book(String image,String title, String authors,  int averageRating) {
        this.image = image;
        this.title = title;
        this.authors = authors;
        this.averageRating = averageRating;
    }

    // Constructor for Edit book
    public Book(String status, int userRating, Date date, boolean favorite) {
        this.status = status;
        this.userRating = userRating;
        this.date = new Date();
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getImage() {
        return image;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public int getUserRating() {
        return userRating;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    public Date getDate() {
        return date;
    }

    public boolean getFavorite() {
        return favorite;
    }

}