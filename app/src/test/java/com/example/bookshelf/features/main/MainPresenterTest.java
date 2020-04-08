package com.example.bookshelf.features.main;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.LoadBookUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {
    @InjectMocks
    MainPresenter presenter;
    private LoadBookUseCase useCase = mock(LoadBookUseCase.class);
    private Navigator navigator = mock(Navigator.class);
    private MainContract.View view = mock(MainContract.View.class);
    private List<Book> books;
    private Book book;

    @BeforeEach
    void prepareData() {
        presenter = new MainPresenter(useCase, navigator, view);

        books = new ArrayList<>();
        book = new Book();
        book.setAuthors("Фрай");
        book.setTitle("Чужак");
    }

    @Test
    public void mainPresenterLoadEmptyList() {
        presenter.onStart();
        verify(useCase).run();
        when(useCase.run()).thenReturn(books);
        verify(view).hideList();
    }

    @Test
    public void mainPresenterLoadListOfBooks() {
        presenter.onStart();
        verify(useCase).run();
        when(useCase.run()).thenReturn(books);
        verify(view).showList(books);
    }

    @Test
    public void mainPresenterViewLoadListOfBooks() {
        view.showList(books);
        verify(view).showList(books);
    }

    @Test
    public void mainPresenterOpenBook() {
        presenter.openBook(book);
        verify(navigator).openBook(book);
    }

    @Test
    public void mainPresenterOpenNull() {
        presenter.openBook(null);
        verify(navigator).openBook(null);
    }

    @Test
    public void mainPresenterEditBook() {
        presenter.editBook(book);
        verify(navigator).editBook(book);
    }

    @Test
    public void mainPresenterEditNull() {
        presenter.editBook(null);
        verify(navigator).editBook(null);
    }

    @Test
    public void mainPresenterOpenBookChallenge() {
        presenter.openBookChallenge();
        verify(navigator).openBookChallenge();
    }

    @Test
    public void mainPresenterOpenSearch() {
        presenter.openSearch();
        verify(navigator).openSearch();
    }
}
