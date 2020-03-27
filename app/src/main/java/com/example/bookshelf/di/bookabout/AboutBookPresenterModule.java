package com.example.bookshelf.di.bookabout;

import com.example.bookshelf.features.bookabout.AboutBookContract;
import com.example.bookshelf.features.bookabout.AboutBookPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutBookPresenterModule {
    @Provides
    public AboutBookContract.Presenter providesPresenter() {
        return new AboutBookPresenter();
    }
}
