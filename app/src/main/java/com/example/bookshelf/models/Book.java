package com.example.bookshelf.models;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {

    private String title;
    private String authors;
    private String imageUrl;
    private String publisher;
    private String lang;
    private String description;
    private int pageCount;
    private float userRating;
    private float averageRating;
    private boolean isFavorite;
    private Date publishedDate;
    private Date readDate;
    private BookStatus status;

    public enum BookStatus implements Serializable{
        READ("Read"),
        WANT_TO_READ("Want to read"),
        READING("Reading"),
        NOT_READING("Not reading");

        private String status;
        BookStatus (String status){
            this.status = status;
        }

        @Override public String toString(){
            return status;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public void setImageURL(String imageURL) {
        this.imageUrl = imageURL;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date dateOfReading) {
        this.readDate = dateOfReading;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
