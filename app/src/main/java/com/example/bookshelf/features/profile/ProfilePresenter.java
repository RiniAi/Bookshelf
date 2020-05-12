package com.example.bookshelf.features.profile;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import javax.inject.Inject;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;

    @Inject
    public ProfilePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase) {
        this.searchBookWithStatusUseCase = searchBookWithStatusUseCase;
    }

    @Override
    public void onStart() {
        countNumberBooks();
    }

    private void countNumberBooks() {
        view.setCountNumberBooks(
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.IN_THE_PROCESS_OF_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.PLAN_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.FINISH_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.QUIT_READING).size())));
    }
}
