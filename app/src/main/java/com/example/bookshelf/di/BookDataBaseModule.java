package com.example.bookshelf.di;

import android.content.Context;

import androidx.room.Room;

import com.example.bookshelf.database.BookDao;
import com.example.bookshelf.database.BookDatabase;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDataBaseModule {
    @Inject
    Context context;

    public BookDataBaseModule(Context context) {
        this.context = context;
    }

    @Provides
    public BookDatabase providesDatabase() {
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
