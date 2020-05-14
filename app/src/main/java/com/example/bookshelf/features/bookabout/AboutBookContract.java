package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.book.Book;

public interface AboutBookContract {
    interface View {
        void showBook(Book book);

        void showBookCover(String cover);

        void showBookBrokenCover();

        void showErrorMessage();
    }

    interface Presenter {
        void onStartWithData(Bundle bundle);
    }
}
