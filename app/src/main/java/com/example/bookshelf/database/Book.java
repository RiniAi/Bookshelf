package com.example.bookshelf.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.bookshelf.R;

import java.io.Serializable;

@Entity(tableName = "books", primaryKeys = {"title", "authors"})
public class Book implements Serializable {
    @NonNull
    public String title;
    @NonNull
    public String authors;
    public float averageRating;
    public float userRating;
    public boolean isFavorite;
    public String readDate;
    public String publisher;
    public String publishedDate;
    public int pageCount;
    public String lang;
    @TypeConverters(BookStatusConverter.class)
    BookStatus status;

    public enum BookStatus implements Serializable {
        IN_THE_PROCESS_OF_READING(R.string.edit_book_process_status),
        PLAN_READING(R.string.edit_book_plan_status),
        FINISH_READING(R.string.edit_book_finish_status),
        QUIT_READING(R.string.edit_book_quit_status);

        private String status;
        private int statusResId;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStatusResId() {
            return statusResId;
        }

        public void setStatusResId(int statusResId) {
            this.statusResId = statusResId;
        }

        BookStatus(int statusResId) {
            this.statusResId = statusResId;
        }

        public static void resolveStatuses(Context context, BookStatus[] statuses){
            for (BookStatus status: statuses){
                status.setStatus(context.getString(status.getStatusResId()));
            }
        }

        @Override
        public String toString() {
            return status;
        }
    }

    public boolean isFinishedOrQuit() {
        if (getStatus() == BookStatus.FINISH_READING || getStatus() == BookStatus.QUIT_READING)
            return true;
        else {
            return false;
        }
    }

    public String imageLinks;
    public String description;

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

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.isFavorite = favorite;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getLanguage() {
        return lang;
    }

    public void setLanguage(String language) {
        this.lang = language;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
