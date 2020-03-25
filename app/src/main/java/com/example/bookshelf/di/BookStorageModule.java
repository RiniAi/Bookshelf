package com.example.bookshelf.di;

import com.example.bookshelf.App;
import com.example.bookshelf.database.BookDao;
import com.example.bookshelf.database.BookStorage;

import dagger.Module;
import dagger.Provides;

@Module
public class BookStorageModule {

    @Provides
    public BookStorage providesBookStorage(){
        return new BookStorage();
    }

    @Provides
    public BookDao providesBookDao(){
        return App.getInstance().getDatabase().bookDao();
    }
}
