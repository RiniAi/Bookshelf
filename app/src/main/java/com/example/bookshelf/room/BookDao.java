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

    @Query("SELECT * FROM books WHERE title = :title AND authors = :authors")
    Book findBookTitleAndAuthor(String title, String authors);

    @Query("SELECT * FROM books WHERE status = :status")
    List<Book> getBookStatusReading(String status);

    @Query("SELECT * FROM books WHERE status = :read OR status = :wantToRead OR status = :reading OR status = :notReading")
    List<Book> getBookStatus(String read, String wantToRead, String reading, String notReading);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Book> books);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book book);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Book book);

    @Delete
    void delete(Book book);
}
