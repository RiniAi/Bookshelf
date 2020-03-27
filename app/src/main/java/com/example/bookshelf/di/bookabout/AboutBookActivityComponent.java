package com.example.bookshelf.di.bookabout;

import com.example.bookshelf.features.bookabout.AboutBookActivity;

import dagger.Subcomponent;

@Subcomponent
public interface AboutBookActivityComponent {
    void inject(AboutBookActivity activity);
}
