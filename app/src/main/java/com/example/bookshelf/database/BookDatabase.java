
package com.example.bookshelf.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class, BookChallenge.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    public abstract BookChallengeDao bookChallengeDao();
}
