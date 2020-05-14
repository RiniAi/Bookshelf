package com.example.bookshelf.database.bookChallenge;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookChallengeDao {
    @Query("SELECT * FROM challenges")
    List<BookChallenge> getAll();

    @Query("SELECT * FROM challenges WHERE year = :year")
    BookChallenge findBookChallengeYear (int year);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookChallenge challenge);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(BookChallenge challenge);
}
