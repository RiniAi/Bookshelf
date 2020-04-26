package com.example.bookshelf.features.main;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.LoadBookUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainPresenterTest {
    @InjectMocks
    MainPresenter presenter;
    @Mock
    LoadBookUseCase useCase;
    @Mock
    Navigator navigator;
    @Mock
    MainContract.View view;

    private Book book;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        presenter.setView(view);
        book = new Book();
        books = new ArrayList<>();
    }

    @Test
    void presenterLoadListOfBooks() {
        books.add(book);
        when(useCase.run()).thenReturn(books);
        presenter.onStart();
        verify(useCase).run();
        verify(view).showList(books);
    }

    @Test
    void presenterLoadEmptyList() {
        when(useCase.run()).thenReturn(books);
        presenter.onStart();
        verify(useCase).run();
        verify(view).hideList();
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
    void presenterOpenBookChallenge() {
        presenter.openBookChallenge();
        verify(navigator).openBookChallenge();
    }

    @Test
    void presenterOpenSearch() {
        presenter.openSearch();
        verify(navigator).openSearch();
    }
}
