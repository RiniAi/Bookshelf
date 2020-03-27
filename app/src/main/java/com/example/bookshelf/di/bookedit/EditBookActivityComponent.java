package com.example.bookshelf.di.bookedit;

import com.example.bookshelf.features.bookedit.EditBookActivity;

import dagger.Subcomponent;

@Subcomponent
public interface EditBookActivityComponent {
    void inject(EditBookActivity activity);
}
