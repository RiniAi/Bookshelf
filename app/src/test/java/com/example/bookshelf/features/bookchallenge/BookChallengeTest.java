package com.example.bookshelf.features.bookchallenge;

import android.content.SharedPreferences;

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

import static com.example.bookshelf.features.bookchallenge.BookChallengePresenter.STORAGE_COUNTER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookChallengeTest {
    @InjectMocks
    BookChallengePresenter presenter;
    @Mock
    BookChallengeContract.View view;
    @Mock
    SearchBookWithStatusUseCase useCase;
    @Mock
    SharedPreferences sharedPreferences;
    @Mock
    Navigator navigator;

    private List<Book> books;
    private Book book;

    @BeforeEach
    void setUp() {
        presenter.setView(view);
        books = new ArrayList<>();
        book = new Book();
    }

    @Test
    void bookChallengePresenterLoadCounter() {
        String counter = "5";
        when(sharedPreferences.getString(STORAGE_COUNTER, "0")).thenReturn(counter);
        presenter.onStart();
        verify(view).changeCounter(counter);
        verify(view).changeCounterForBar(5);
    }

    @Test
    void bookChallengePresenterLoadNullCounter() {
        presenter.onStart();
        verify(view).changeCounter(null);
        verify(view).changeCounterForBar(0);
    }

    @Test
    void bookChallengePresenterLoadEmptyList() {
        when(useCase.run()).thenReturn(books);
        presenter.onStart();
        verify(useCase).run();
        verify(view).hideList();
    }

    @Test
    void bookChallengePresenterLoadNotEmptyList() {
        books.add(book);
        when(useCase.run()).thenReturn(books);
        presenter.onStart();
        verify(useCase).run();
        verify(view).showList(books);
        verify(view).changeProgress("1");
    }

    @Test
    void bookChallengePresenterChangeCounter() {
        presenter.onProgressChanged(5);
        verify(view).changeCounter("5");
    }

    @Test
    void bookChallengePresenterOpenBook() {
        presenter.openBook(book);
        verify(navigator).openBook(book);
    }

    @Test
    void bookChallengePresenterOpenMain() {
        presenter.openMain();
        verify(navigator).openMain();
    }
}
