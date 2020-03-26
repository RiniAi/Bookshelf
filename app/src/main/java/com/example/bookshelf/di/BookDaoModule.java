package com.example.bookshelf.di;

import com.example.bookshelf.App;
import com.example.bookshelf.database.BookDao;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDaoModule {
    @Provides
    public BookDao providesBookDao(){
        return App.getInstance().getDatabase().bookDao();
    }
}
