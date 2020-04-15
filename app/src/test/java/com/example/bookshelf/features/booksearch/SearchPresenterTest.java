package com.example.bookshelf.features.booksearch;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookssearch.SearchCall;
import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.usecases.RequestBooksUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SearchPresenterTest {
    @InjectMocks
    SearchPresenter presenter;
    @Mock
    RequestBooksUseCase requestBooksUseCase;
    @Mock
    SearchContract.View view;
    @Mock
    Navigator navigator;

    private Book book;

    @BeforeEach
    void setUp() {
        presenter.setView(view);
        book = new Book();
    }

    @Test
    void searchPresenterRunSomeRequest() {
        presenter.searchBook("Tom");
        verify(requestBooksUseCase).run(anyString(), isA(SearchCall.ResponseListener.class));
    }

    @Test
    void searchPresenterRunEmptyRequest() {
        presenter.searchBook("");
        verify(view).showErrorMessage();
    }

    @Test
    void searchPresenterOpenBook() {
        presenter.openBook(book);
        verify(navigator).openBook(book);
    }

    @Test
    void searchPresenterEditBook() {
        presenter.editBook(book);
        verify(navigator).editBook(book);
    }

    @Test
    void searchPresenterOpenMain() {
        presenter.openMain();
        verify(navigator).openMain();
    }

    @Test
    void searchPresenterOpenBookChallenge() {
        presenter.openBookChallenge();
        verify(navigator).openBookChallenge();
    }
}
