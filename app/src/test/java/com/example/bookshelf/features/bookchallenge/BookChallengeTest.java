package com.example.bookshelf.features.bookchallenge;

import android.content.SharedPreferences;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookChallengeTest {
    @InjectMocks
    BookChallengePresenter presenter;
    private BookChallengeContract.View view = mock(BookChallengeContract.View.class);
    private SearchBookWithStatusUseCase useCase = mock(SearchBookWithStatusUseCase.class);
    private SharedPreferences sharedPreferences = mock(SharedPreferences.class);
    private Navigator navigator = mock(Navigator.class);
    private String counter;
    private List<Book> books;
    private Book book = new Book();

    @BeforeEach
    void prepareData() {
        presenter = new  BookChallengePresenter(useCase, sharedPreferences, navigator, view);
        counter = null;
        books = new ArrayList<>();
    }

    @Test
    void bookChallengePresenterLoadNullCounter(){
        presenter.onStart();
        verify(view).changeCounter(counter);
        verify(view).changeCounterForBar(0);
    }

    @Test
    void bookChallengePresenterViewLoadNotNullCounter(){
        counter = "5";
        when(sharedPreferences.getString(BookChallengePresenter.STORAGE_COUNTER, "0")).thenReturn(counter);
        presenter.onStart();
        verify(view).changeCounter(counter);
        verify(view).changeCounterForBar(5);
    }

    @Test
    void bookChallengePresenterLoadEmptyList(){
        when(useCase.run()).thenReturn(books);
        presenter.onStart();
        verify(useCase).run();
        verify(view).hideList();
    }

    @Test
    void bookChallengePresenterLoadNotEmptyList(){
        books.add(book);
        when(useCase.run()).thenReturn(books);
        presenter.onStart();
        verify(useCase).run();
        verify(view).showList(books);
        verify(view).changeProgress("1");
    }

    @Test
    void bookChallengePresenterChangeNotNullCounter(){
        presenter.onProgressChanged(5);
        verify(view).changeCounter("5");
    }

    @Test
    void bookChallengePresenterChangeNullCounter(){
        presenter.onProgressChanged(0);
        verify(view).changeCounter("0");
    }

    @Test
    public void bookChallengePresenterOpenBook() {
        presenter.openBook(book);
        verify(navigator).openBook(book);
    }

    @Test
    public void bookChallengePresenterOpenNull() {
        presenter.openBook(null);
        verify(navigator).openBook(null);
    }

    @Test
    public void bookChallengePresenterOpenMain() {
        presenter.openMain();
        verify(navigator).openMain();
    }
}
