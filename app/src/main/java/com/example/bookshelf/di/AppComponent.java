package com.example.bookshelf.di;

import android.content.Context;

import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.bookchallenge.BookChallengeActivity;
import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookedit.EditBookPresenter;
import com.example.bookshelf.features.bookssearch.SearchActivity;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.features.main.MainActivity;
import com.example.bookshelf.features.main.MainPresenter;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDaoModule.class,
        MainPresenterModule.class,
        SearchPresenterModule.class,
        EditBookPresenterModule.class,
        AboutBookPresenterModule.class,
        BookChallengePresenterModule.class,

})

public interface AppComponent {
    Context context();

    void injectMainPresenter(MainPresenter presenter);

    void injectSearchPresenter(SearchPresenter presenter);

    void injectEditBookPresenter(EditBookPresenter presenter);

    void injectBookChallengePresenter(BookChallengePresenter presenter);

    void injectMainActivity(MainActivity activity);

    void injectSearchActivity(SearchActivity activity);

    void injectEditBookActivity(EditBookActivity activity);

    void injectAboutBookActivity(AboutBookActivity activity);

    void injectBookChallengeActivity(BookChallengeActivity activity);
}
