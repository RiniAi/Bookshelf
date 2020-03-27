package com.example.bookshelf.di;

import com.example.bookshelf.di.bookabout.AboutBookActivityComponent;
import com.example.bookshelf.di.bookabout.AboutBookPresenterModule;
import com.example.bookshelf.di.bookchallenge.BookChallengeActivityComponent;
import com.example.bookshelf.di.bookchallenge.BookChallengePresenterComponent;
import com.example.bookshelf.di.bookchallenge.BookChallengePresenterModule;
import com.example.bookshelf.di.bookedit.EditBookActivityComponent;
import com.example.bookshelf.di.bookedit.EditBookPresenterComponent;
import com.example.bookshelf.di.bookedit.EditBookPresenterModule;
import com.example.bookshelf.di.bookssearch.SearchActivityComponent;
import com.example.bookshelf.di.bookssearch.SearchPresenterComponent;
import com.example.bookshelf.di.bookssearch.SearchPresenterModule;
import com.example.bookshelf.di.main.MainActivityComponent;
import com.example.bookshelf.di.main.MainPresenterComponent;
import com.example.bookshelf.di.main.MainPresenterModule;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDaoModule.class,
        MainPresenterModule.class,
        SearchPresenterModule.class,
        EditBookPresenterModule.class,
        AboutBookPresenterModule.class,
        BookChallengePresenterModule.class})

public interface AppComponent {
    MainActivityComponent mainActivityComponent();

    MainPresenterComponent mainPresenterComponent();

    SearchActivityComponent searchActivityComponent();

    SearchPresenterComponent searchPresenterComponent();

    EditBookActivityComponent editBookActivityComponent();

    EditBookPresenterComponent editBookPresenterComponent();

    AboutBookActivityComponent aboutBookActivityComponent();

    BookChallengeActivityComponent bookChallengeActivityComponent();

    BookChallengePresenterComponent bookChallengePresenterComponent();
}
