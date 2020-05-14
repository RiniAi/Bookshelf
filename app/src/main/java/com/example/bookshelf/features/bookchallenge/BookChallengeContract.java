package com.example.bookshelf.features.bookchallenge;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.features.bookabout.AboutBookFragment;

import java.util.List;

public interface BookChallengeContract {
    interface View {
        void changeCounter(String counter);

        void changeCounterForBar(int counter);

        void hideList();

        void showList(List<Book> books);

        void changeProgress(String progress);

        void showCounterSavedMessage();

        void openBook(AboutBookFragment newInstance);
    }

    interface Presenter {
        void onStart();

        void saveOrUpdateCounterAndProgress();

        void onProgressChanged(int i);

        void saveCounter(String counter);

        void openBook(Book book);
    }
}
