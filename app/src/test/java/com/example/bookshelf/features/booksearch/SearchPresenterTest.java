package com.example.bookshelf.features.booksearch;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.usecases.RequestBooksUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Mock
    Single<List<Book>> books;

    private Book book;

    @BeforeEach
    void setUp() {
        presenter.setView(view);
        book = new Book();

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void presenterRunSomeRequest() {
        RequestBooksUseCase.Params params = new RequestBooksUseCase.Params("Tom");
        when(requestBooksUseCase.run(params)).thenReturn(books);
        presenter.searchBook("Tom");
        verify(requestBooksUseCase).run(params);
    }

    @Test
    void presenterRunEmptyRequest() {
        presenter.searchBook("");
        verify(view).showErrorMessage();
    }

    @Test
    void presenterOpenBook() {
        presenter.openBook(book);
        verify(navigator).openBook(book);
    }

    @Test
    void presenterEditBook() {
        presenter.editBook(book);
        verify(navigator).editBook(book);
    }

    @Test
    void presenterOpenMain() {
        presenter.openMain();
        verify(navigator).openMain();
    }

    @Test
    void presenterOpenBookChallenge() {
        presenter.openBookChallenge();
        verify(navigator).openBookChallenge();
    }
}
