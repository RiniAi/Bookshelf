package com.example.bookshelf;

import android.app.Application;

import androidx.room.Room;

import com.example.bookshelf.database.BookDatabase;
import com.example.bookshelf.di.AppComponent;
import com.example.bookshelf.di.AppModule;
import com.example.bookshelf.di.DaggerAppComponent;

public class App extends Application {
    public static App instance;
    private BookDatabase database;

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(App.this))
                .build();

        createDatabase();
    }

    private void createDatabase() {
        instance = this;
        database = Room.databaseBuilder(this, BookDatabase.class, "db_bookss")
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
