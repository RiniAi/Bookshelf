package com.example.bookshelf.di;

import com.example.bookshelf.features.bookabout.AboutBookContract;
import com.example.bookshelf.features.bookabout.AboutBookPresenter;
import com.example.bookshelf.features.bookchallenge.BookChallengeContract;
import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;
import com.example.bookshelf.features.bookedit.EditBookContract;
import com.example.bookshelf.features.bookedit.EditBookPresenter;
import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.features.main.MainContract;
import com.example.bookshelf.features.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public MainContract.Presenter providesMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    public SearchContract.Presenter providesSearchPresenter() {
        return new SearchPresenter();
    }

    @Provides
    public EditBookContract.Presenter providesEditBookPresenter() {
        return new EditBookPresenter();
    }

    @Provides
    public AboutBookContract.Presenter providesAboutBookAboutBookPresenter() {
        return new AboutBookPresenter();
    }

    @Provides
    public BookChallengeContract.Presenter providesBookChallengePresenter() {
        return new BookChallengePresenter();
    }
}
