package com.example.bookshelf.features.profile;

import android.content.SharedPreferences;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;
import com.example.bookshelf.usecases.SearchListOfBookChallengeUseCase;

import javax.inject.Inject;

import static com.example.bookshelf.features.splash.SplashPresenter.EMAIL;
import static com.example.bookshelf.features.splash.SplashPresenter.PASSWORD;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;
    @Inject
    SearchListOfBookChallengeUseCase searchListOfBookChallengeUseCase;
    @Inject
    Navigator navigator;

    @Inject
    public ProfilePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, SearchListOfBookChallengeUseCase searchListOfBookChallengeUseCase,
                            Navigator navigator) {
        this.searchBookWithStatusUseCase = searchBookWithStatusUseCase;
        this.searchListOfBookChallengeUseCase = searchListOfBookChallengeUseCase;
        this.navigator = navigator;
    }

    @Override
    public void onStart() {
        countNumberBooks();
        loadStatisticsBookChallenge();
    }

    @Override
    public void openAuthentication() {
        loadDateForLoginFromSharedPreferences();
        navigator.openAuthentication();
    }

    private void loadDateForLoginFromSharedPreferences() {
        SharedPreferences sharedPreferences = view.initSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, "");
        editor.putString(PASSWORD, "");
        editor.apply();
    }

    private void countNumberBooks() {
        view.setCountNumberBooks(
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.IN_THE_PROCESS_OF_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.PLAN_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.FINISH_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.QUIT_READING).size())));
    }

    private void loadStatisticsBookChallenge() {
        view.loadStatisticsBookChallenge(searchListOfBookChallengeUseCase.run());
    }
}
