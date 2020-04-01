package com.example.bookshelf.di;

import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.bookchallenge.BookChallengeActivity;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookssearch.SearchActivity;
import com.example.bookshelf.features.main.MainActivity;

import dagger.Subcomponent;

@Subcomponent
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(SearchActivity activity);
    void inject(EditBookActivity activity);
    void inject(AboutBookActivity activity);
    void inject(BookChallengeActivity activity);
}
