package com.example.bookshelf.di.bookssearch;

import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchPresenterModule {
    @Provides
    public SearchContract.Presenter providesPresenter() {
        return new SearchPresenter();
    }
}
