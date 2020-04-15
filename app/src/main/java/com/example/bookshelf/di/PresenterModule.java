package com.example.bookshelf.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.features.bookabout.AboutBookContract;
import com.example.bookshelf.features.bookabout.AboutBookPresenter;
import com.example.bookshelf.features.bookchallenge.BookChallengeContract;
import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;
import com.example.bookshelf.features.bookedit.EditBookContract;
import com.example.bookshelf.features.bookedit.EditBookPresenter;
import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.features.main.MainContract;
import com.example.bookshelf.features.main.MainPresenter;
import com.example.bookshelf.usecases.DeleteBookUseCase;
import com.example.bookshelf.usecases.InsertOrUpdateBookUseCase;
import com.example.bookshelf.usecases.LoadBookUseCase;
import com.example.bookshelf.usecases.RequestBooksUseCase;
import com.example.bookshelf.usecases.SearchBookUseCase;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public MainContract.Presenter providesMainPresenter(LoadBookUseCase loadBookUseCase, Navigator navigator) {
        return new MainPresenter(loadBookUseCase, navigator);
    }

    @Provides
    public SearchContract.Presenter providesSearchPresenter(RequestBooksUseCase requestBooksUseCase, Navigator navigator) {
        return new SearchPresenter(requestBooksUseCase, navigator);
    }

    @Provides
    public EditBookContract.Presenter providesEditBookPresenter(Context context, SearchBookUseCase searchBookUseCase, InsertOrUpdateBookUseCase insertOrUpdateBookUseCase,
                                                                DeleteBookUseCase deleteBookUseCase
    ) {
        return new EditBookPresenter(context, searchBookUseCase, insertOrUpdateBookUseCase, deleteBookUseCase);
    }

    @Provides
    public AboutBookContract.Presenter providesAboutBookAboutBookPresenter() {
        return new AboutBookPresenter();
    }

    @Provides
    public BookChallengeContract.Presenter providesBookChallengePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, SharedPreferences sharedPreferences,
                                                                          Navigator navigator) {
        return new BookChallengePresenter(searchBookWithStatusUseCase, sharedPreferences, navigator);
    }
}
