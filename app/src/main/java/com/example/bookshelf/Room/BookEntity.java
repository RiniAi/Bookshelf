package com.example.bookshelf.Room;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "books", primaryKeys = {"title", "authors"})
public class BookEntity {
    @NonNull
    public String title;
    @NonNull
    public String authors;
    public float averageRating;
    public String imageLinks;

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

    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }
}
