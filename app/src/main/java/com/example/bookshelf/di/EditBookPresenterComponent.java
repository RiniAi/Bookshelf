package com.example.bookshelf.di;

import com.example.bookshelf.features.bookedit.EditBookPresenter;

import dagger.Subcomponent;

@Subcomponent
public interface EditBookPresenterComponent {
    void inject(EditBookPresenter presenter);
}
