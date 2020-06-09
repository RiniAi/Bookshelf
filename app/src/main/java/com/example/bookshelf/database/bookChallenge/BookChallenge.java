package com.example.bookshelf.database.bookChallenge;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "challenges", primaryKeys = {"year"})
public class BookChallenge implements Serializable {
    @NonNull
    public int year;
    public int progress;
    public int counter;

    // Replacing the constructor with the factory method
    public static BookChallenge createBookChallenge() {
        return new BookChallenge();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookChallenge that = (BookChallenge) o;

        return year == that.year;
    }

    @Override
    public int hashCode() {
        return year;
    }

    @Override
    public String toString() {
        return "BookChallenge{" +
                "year=" + year +
                ", progress=" + progress +
                ", counter=" + counter +
                '}';
    }
}
