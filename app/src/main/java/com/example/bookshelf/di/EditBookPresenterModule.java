package com.example.bookshelf.di;

import com.example.bookshelf.features.bookedit.EditBookContract;
import com.example.bookshelf.features.bookedit.EditBookPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EditBookPresenterModule {
    @Provides
    public EditBookContract.Presenter providesPresenter() {
        return new EditBookPresenter();
    }
}
