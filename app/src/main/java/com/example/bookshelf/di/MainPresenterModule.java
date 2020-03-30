package com.example.bookshelf.di;

import com.example.bookshelf.features.main.MainContract;
import com.example.bookshelf.features.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {
    @Provides
    public MainContract.Presenter providesPresenter() {
        return new MainPresenter();
    }
}
