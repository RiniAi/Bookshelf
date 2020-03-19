package com.example.bookshelf.features.bookchallenge;

import android.widget.SeekBar;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;
import com.example.bookshelf.database.Book;

import java.util.List;

public interface BookChallengeContract {
    interface View extends BaseView {

        void setCounter(String count);

        void setProgressBar(int count);

        void showList(List<Book> books);

        void setSizeList(String size);

        void setProgressCounter(String count);

        void showSaveCounter();
    }

    interface Presenter extends BasePresenter {


        void openBook(Book book);

        void openMain();
    }
}
