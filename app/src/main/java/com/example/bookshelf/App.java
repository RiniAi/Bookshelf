package com.example.bookshelf;

import android.app.Application;

import androidx.room.Room;

import com.example.bookshelf.Room.BookDatabase;

public class App extends Application {
    public static App instance;
    private BookDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, BookDatabase.class, "database_book")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public BookDatabase getDatabase() {
        return database;
    }
}
