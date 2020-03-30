package com.example.bookshelf.di;

import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;
import com.example.bookshelf.features.bookedit.EditBookPresenter;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.features.main.MainPresenter;

import dagger.Subcomponent;

@Subcomponent
public interface PresenterComponent {
    void inject(MainPresenter presenter);
    void inject(SearchPresenter presenter);
    void inject(EditBookPresenter presenter);
    void inject(BookChallengePresenter presenter);
}
