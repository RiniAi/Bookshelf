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
    List<BookEntity> getList();

    @Insert
    void insert(List<BookEntity> list);

    @Update
    void update(BookEntity bookEntity);

    @Delete
    void delete(BookEntity bookEntity);
}
