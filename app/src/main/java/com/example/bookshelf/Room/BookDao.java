package com.example.bookshelf.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    List<BookEntity> getBookEntityList();

    @Insert
    void insertBookEntity(BookEntity bookEntity);

    @Update
    void updateBookEntity(BookEntity bookEntity);

    @Delete
    void deleteBookEntity(BookEntity bookEntity);
}
