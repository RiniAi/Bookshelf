package com.example.bookshelf.features.bookchallenge;

import android.widget.SeekBar;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;
import com.example.bookshelf.database.Book;

import java.util.List;

public interface BookChallengeContract {
    interface View extends BaseView {
        void initCounter(String count);

        void initSeekBar(int count);

        void initRecyclerView();

        void showList(List<Book> books, String size);

        void setProgress(String counter);
    }

    interface Presenter extends BasePresenter {
        void loadCounter();

        void loadBooks();

        void convertCount(SeekBar seekBar);

        void saveCount(SeekBar seekBar, String count);

        void openBook(Book book);

        void openMain();
    }
}
