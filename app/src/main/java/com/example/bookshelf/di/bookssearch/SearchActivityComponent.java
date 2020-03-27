package com.example.bookshelf.di.bookssearch;

import com.example.bookshelf.features.bookssearch.SearchActivity;

import dagger.Subcomponent;

@Subcomponent
public interface SearchActivityComponent {
    void inject(SearchActivity activity);
}
