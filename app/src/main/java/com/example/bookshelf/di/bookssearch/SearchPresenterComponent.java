package com.example.bookshelf.di.bookssearch;

import com.example.bookshelf.features.bookssearch.SearchPresenter;

import dagger.Subcomponent;

@Subcomponent
public interface SearchPresenterComponent {
    void inject(SearchPresenter presenter);
}
