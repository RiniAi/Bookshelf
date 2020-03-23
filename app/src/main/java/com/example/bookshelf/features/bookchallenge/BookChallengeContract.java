package com.example.bookshelf.features.bookchallenge;

import com.example.bookshelf.database.Book;

import java.util.List;

public interface BookChallengeContract {
    interface View {
        void changeCounter(String count);

        void changeCounterForBar(int count);

        void showList(List<Book> books);

        void changeProgress(String size);

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
