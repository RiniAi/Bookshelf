package com.example.bookshelf.di.main;

import com.example.bookshelf.features.main.MainPresenter;

import dagger.Subcomponent;

@Subcomponent
public interface MainPresenterComponent {
    void inject(MainPresenter presenter);
}
