
package com.example.bookshelf.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.book.BookDao;
import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.database.bookChallenge.BookChallengeDao;

@Database(entities = {Book.class, BookChallenge.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    public abstract BookChallengeDao bookChallengeDao();
}
