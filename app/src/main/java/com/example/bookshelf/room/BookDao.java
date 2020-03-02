package com.example.bookshelf.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    List<Book> getList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Book> books);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Book book);

    @Delete
    void delete(Book book);
}
