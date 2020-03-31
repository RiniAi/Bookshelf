package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

public interface AboutBookContract {
    interface View {
        void showBook(Book book);

        void showBookCover(String cover);

        void showBookBrokenCover();
    }

    interface Presenter {
        void onStartWithData(Bundle bundle);
    }
}
