package com.example.bookshelf.features.bookchallenge;

import android.content.SharedPreferences;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.book.Book;
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
    void presenterLoadCounter() {
        String counter = "5";
        when(sharedPreferences.getString(STORAGE_COUNTER, "0")).thenReturn(counter);
        presenter.onStart();
        verify(view).changeCounter(counter);
        verify(view).changeCounterForBar(5);
    }

    @Test
    void presenterLoadEmptyList() {
        when(useCase.run(Book.BookStatus.FINISH_READING)).thenReturn(books);
        presenter.onStart();
        verify(useCase).run(Book.BookStatus.FINISH_READING);
        verify(view).hideList();
    }

    @Test
    void presenterLoadNotEmptyList() {
        books.add(book);
        when(useCase.run(Book.BookStatus.FINISH_READING)).thenReturn(books);
        presenter.onStart();
        verify(useCase).run(Book.BookStatus.FINISH_READING);
        verify(view).showList(books);
        verify(view).changeProgress("1");
    }

    @Test
    void presenterChangeCounter() {
        presenter.onProgressChanged(5);
        verify(view).changeCounter("5");
    }

    @Test
    void presenterOpenBook() {
        presenter.openBook(book);
        verify(navigator).openBook(book);
    }
}
