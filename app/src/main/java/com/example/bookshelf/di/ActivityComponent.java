package com.example.bookshelf.di;

import com.example.bookshelf.features.bookabout.AboutBookFragment;
import com.example.bookshelf.features.bookchallenge.BookChallengeFragment;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookssearch.SearchFragment;
import com.example.bookshelf.features.listofbooks.BookStatusFragment;
import com.example.bookshelf.features.profile.ProfileFragment;

import dagger.Subcomponent;

@Subcomponent
public interface ActivityComponent {
    void inject(BookStatusFragment fragment);
    void inject(SearchFragment fragment);
    void inject(EditBookActivity activity);
    void inject(AboutBookFragment activity);
    void inject(BookChallengeFragment fragment);
    void inject(ProfileFragment fragment);
}
