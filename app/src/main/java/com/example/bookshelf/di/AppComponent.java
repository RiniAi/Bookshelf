package com.example.bookshelf.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.bookabout.AboutBookPresenterModule;
import com.example.bookshelf.features.bookchallenge.BookChallengeActivity;
import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;
import com.example.bookshelf.features.bookchallenge.BookChallengePresenterModule;
import com.example.bookshelf.features.main.MainActivity;
import com.example.bookshelf.features.main.MainPresenter;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDaoModule.class,
        MainPresenterModule.class,
        AboutBookPresenterModule.class,
        BookChallengePresenterModule.class,

})

public interface AppComponent {
    Context context();

    SharedPreferences sharedPreferences();

    void injectMainPresenter(MainPresenter presenter);

    void injectBookChallengePresenter(BookChallengePresenter presenter);

    void injectMainActivity(MainActivity activity);

    void injectAboutBookActivity(AboutBookActivity activity);

    void injectBookChallengeActivity(BookChallengeActivity activity);
}
