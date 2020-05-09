package com.example.bookshelf.features.listofbooks;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import java.util.List;

import javax.inject.Inject;

public class ListOfBooksPresenter extends BasePresenter<ListOfBooksContract.View> implements ListOfBooksContract.Presenter {
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;
    @Inject
    Navigator navigator;

    @Inject
    public ListOfBooksPresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, Navigator navigator) {
        this.searchBookWithStatusUseCase = searchBookWithStatusUseCase;
        this.navigator = navigator;
    }

    @Override
    public void onStart(Book.BookStatus status) {
        List<Book> books = searchBookWithStatusUseCase.run(status);
        if (books.isEmpty()) {
            view.hideList();
        } else {
            view.showList(books);
        }
    }

    @Override
    public void openBook(Book book) {
        navigator.openBook(book);
    }

    @Override
    public void editBook(Book book) {
        navigator.editBook(book);
    }
}
