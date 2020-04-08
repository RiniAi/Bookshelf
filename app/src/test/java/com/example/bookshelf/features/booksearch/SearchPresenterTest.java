package com.example.bookshelf.features.booksearch;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookssearch.SearchCall;
import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.usecases.RequestBooksUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SearchPresenterTest {
    @InjectMocks
    SearchPresenter presenter;
    private SearchContract.View view = mock(SearchContract.View.class);
    private RequestBooksUseCase useCase = mock(RequestBooksUseCase.class);
    private SearchCall.ResponseListener responseListener = mock(SearchCall.ResponseListener.class);
    private Navigator navigator = mock(Navigator.class);
    private List<Book> books;
    private Book book;

    @BeforeEach
    void prepareData() {
        presenter = new  SearchPresenter(useCase, navigator, view);
        books = new ArrayList<>();
        book = new Book();
    }

    @Test
    void searchPresenterEmptyRequestShowErrorMessage(){
        presenter.searchBook("");
        verify(view).showErrorMessage();
    }

    @Test
    void searchPresenterViewSomeRequestShowEmptyView(){
        view.showEmptyView();
        verify(view).showEmptyView();
    }

    @Test
    void searchPresenterViewSomeRequestShowBookList(){
        view.showBooks(books);
        verify(view).showBooks(books);
    }

    @Test
    void searchPresenterViewSomeRequestShowError(){
        view.showError();
        verify(view).showError();
    }
    @Test
    public void searchPresenterOpenBook() {
        presenter.openBook(book);
        verify(navigator).openBook(book);
    }

    @Test
    public void searchPresenterOpenNull() {
        presenter.openBook(null);
        verify(navigator).openBook(null);
    }

    @Test
    public void searchPresenterEditBook() {
        presenter.editBook(book);
        verify(navigator).editBook(book);
    }

    @Test
    public void searchPresenterEditNull() {
        presenter.editBook(null);
        verify(navigator).editBook(null);
    }

    @Test
    public void searchPresenterOpenMain() {
        presenter.openMain();
        verify(navigator).openMain();
    }

    @Test
    public void searchPresenterOpenBookChallenge() {
        presenter.openBookChallenge();
        verify(navigator).openBookChallenge();
    }
}
