package com.example.bookshelf.features.listofbooks;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

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
class ListOfBooksPresenterTest {
    @InjectMocks
    ListOfBooksPresenter presenter;
    @Mock
    SearchBookWithStatusUseCase useCase;
    @Mock
    Navigator navigator;
    @Mock
    ListOfBooksContract.View view;

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
        when(useCase.run(Book.BookStatus.PLAN_READING)).thenReturn(books);
        presenter.onStart(Book.BookStatus.PLAN_READING);
        verify(useCase).run(Book.BookStatus.PLAN_READING);
        verify(view).showList(books);
    }

    @Test
    void presenterLoadEmptyList() {
        when(useCase.run(Book.BookStatus.PLAN_READING)).thenReturn(books);
        presenter.onStart(Book.BookStatus.PLAN_READING);
        verify(useCase).run(Book.BookStatus.PLAN_READING);
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
}
