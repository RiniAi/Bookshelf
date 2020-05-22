package com.example.bookshelf.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.features.authentication.login.LoginContract;
import com.example.bookshelf.features.authentication.login.LoginPresenter;
import com.example.bookshelf.features.authentication.register.RegisterContract;
import com.example.bookshelf.features.authentication.register.RegisterPresenter;
import com.example.bookshelf.features.bookabout.AboutBookContract;
import com.example.bookshelf.features.bookabout.AboutBookPresenter;
import com.example.bookshelf.features.bookchallenge.BookChallengeContract;
import com.example.bookshelf.features.bookchallenge.BookChallengePresenter;
import com.example.bookshelf.features.bookedit.EditBookContract;
import com.example.bookshelf.features.bookedit.EditBookPresenter;
import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.features.listofbooks.BookStatusContract;
import com.example.bookshelf.features.listofbooks.BookStatusPresenter;
import com.example.bookshelf.features.profile.ProfileContract;
import com.example.bookshelf.features.profile.ProfilePresenter;
import com.example.bookshelf.usecases.DeleteBookUseCase;
import com.example.bookshelf.usecases.InsertOrUpdateBookChallengeUseCase;
import com.example.bookshelf.usecases.InsertOrUpdateBookUseCase;
import com.example.bookshelf.usecases.RequestBooksUseCase;
import com.example.bookshelf.usecases.SearchBookChallengeUseCase;
import com.example.bookshelf.usecases.SearchBookUseCase;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;
import com.example.bookshelf.usecases.SearchListOfBookChallengeUseCase;

import dagger.Module;
import dagger.Provides;

@Module
class PresenterModule {
    @Provides
    BookStatusContract.Presenter providesBookStatusPresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, Navigator navigator) {
        return new BookStatusPresenter(searchBookWithStatusUseCase, navigator);
    }

    @Provides
    SearchContract.Presenter providesSearchPresenter(RequestBooksUseCase requestBooksUseCase, Navigator navigator) {
        return new SearchPresenter(requestBooksUseCase, navigator);
    }

    @Provides
    EditBookContract.Presenter providesEditBookPresenter(Context context, SearchBookUseCase searchBookUseCase, InsertOrUpdateBookUseCase insertOrUpdateBookUseCase,
                                                         DeleteBookUseCase deleteBookUseCase) {
        return new EditBookPresenter(context, searchBookUseCase, insertOrUpdateBookUseCase, deleteBookUseCase);
    }

    @Provides
    AboutBookContract.Presenter providesAboutBookAboutBookPresenter() {
        return new AboutBookPresenter();
    }

    @Provides
    BookChallengeContract.Presenter providesBookChallengePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, InsertOrUpdateBookChallengeUseCase challengeUseCase,
                                                                   SearchBookChallengeUseCase searchBookChallengeUseCase, SharedPreferences sharedPreferences,
                                                                   Navigator navigator) {
        return new BookChallengePresenter(searchBookWithStatusUseCase, challengeUseCase, searchBookChallengeUseCase, sharedPreferences, navigator);
    }

    @Provides
    ProfileContract.Presenter providesProfilePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, SearchListOfBookChallengeUseCase searchListOfBookChallengeUseCase) {
        return new ProfilePresenter(searchBookWithStatusUseCase, searchListOfBookChallengeUseCase);
    }

    @Provides
    RegisterContract.Presenter providesRegisterPresenter(Navigator navigator) {
        return new RegisterPresenter(navigator);
    }

    @Provides
    LoginContract.Presenter providesLoginPresenter(Navigator navigator) {
        return new LoginPresenter(navigator);
    }
}
