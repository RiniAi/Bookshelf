package com.example.bookshelf.di;

import android.content.Context;

import androidx.room.Room;

import com.example.bookshelf.database.bookChallenge.BookChallengeDao;
import com.example.bookshelf.database.book.BookDao;
import com.example.bookshelf.database.BookDatabase;

import dagger.Module;
import dagger.Provides;

@Module
class BookDataBaseModule {
    @Provides
    BookDatabase providesDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                BookDatabase.class,
                "db_bookssssss")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    BookDao providesBookDao(BookDatabase bookDataBase) {
        return bookDataBase.bookDao();
    }

    @Provides
    BookChallengeDao providesBookChallengeDao(BookDatabase bookDataBase) {
        return bookDataBase.bookChallengeDao();
    }
}
