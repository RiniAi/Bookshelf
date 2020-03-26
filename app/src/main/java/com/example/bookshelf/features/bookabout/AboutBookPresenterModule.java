package com.example.bookshelf.features.bookabout;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutBookPresenterModule {
    @Provides
    public AboutBookContract.Presenter providesPresenter() {
        return new AboutBookPresenter();
    }
}
