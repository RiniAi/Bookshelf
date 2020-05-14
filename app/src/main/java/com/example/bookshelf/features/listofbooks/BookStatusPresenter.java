package com.example.bookshelf.features.listofbooks;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import java.util.List;

import javax.inject.Inject;

public class BookStatusPresenter extends BasePresenter<BookStatusContract.View> implements BookStatusContract.Presenter {
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;
    @Inject
    Navigator navigator;

    @Inject
    public BookStatusPresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, Navigator navigator) {
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
