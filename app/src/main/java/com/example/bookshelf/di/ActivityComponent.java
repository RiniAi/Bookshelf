package com.example.bookshelf.di;

import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.bookchallenge.BookChallengeFragment;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookssearch.SearchFragment;
import com.example.bookshelf.features.listofbooks.BookStatusFragment;

import dagger.Subcomponent;

@Subcomponent
public interface ActivityComponent {
    void inject(BookStatusFragment activity);
    void inject(SearchFragment activity);
    void inject(EditBookActivity activity);
    void inject(AboutBookActivity activity);
    void inject(BookChallengeFragment activity);
}
