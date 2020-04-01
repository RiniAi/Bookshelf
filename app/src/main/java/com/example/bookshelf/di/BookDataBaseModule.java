package com.example.bookshelf.di;

import android.content.Context;

import androidx.room.Room;

import com.example.bookshelf.database.BookDao;
import com.example.bookshelf.database.BookDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDataBaseModule {
    @Provides
    public BookDatabase providesDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                BookDatabase.class,
                "db_bookss")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    public BookDao providesBookDao(BookDatabase bookDataBase) {
        return bookDataBase.bookDao();
    }
}
