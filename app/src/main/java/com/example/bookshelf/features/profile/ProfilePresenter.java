package com.example.bookshelf.features.profile;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;
import com.example.bookshelf.usecases.SearchListOfBookChallengeUseCase;

import javax.inject.Inject;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;
    @Inject
    SearchListOfBookChallengeUseCase searchListOfBookChallengeUseCase;

    @Inject
    public ProfilePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, SearchListOfBookChallengeUseCase searchListOfBookChallengeUseCase) {
        this.searchBookWithStatusUseCase = searchBookWithStatusUseCase;
        this.searchListOfBookChallengeUseCase = searchListOfBookChallengeUseCase;
    }

    @Override
    public void onStart() {
        countNumberBooks();
        loadStatisticsBookChallenge();
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
