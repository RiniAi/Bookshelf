package com.example.bookshelf.features.bookchallenge;

import com.example.bookshelf.database.Book;

import java.util.List;

public interface BookChallengeContract {
    interface View {
        void setCounter(String count);

        void setProgressBar(int count);

        void showList(List<Book> books);

        void setBooksCount(String size);

        void setProgressCounter(String count);

        void showCounterSavedMessage();
    }

    interface Presenter {
        void onStart();

        void onProgressChanged(int i);

        void saveCounter(String count);

        void openBook(Book book);

        void openMain();
    }
}
