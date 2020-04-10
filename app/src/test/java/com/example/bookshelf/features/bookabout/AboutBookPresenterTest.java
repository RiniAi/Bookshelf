package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AboutBookPresenterTest {
    @InjectMocks
    AboutBookPresenter presenter;
    private AboutBookContract.View view = mock(AboutBookContract.View.class);
    private Bundle bundle = mock(Bundle.class);
    private Book book = new Book();

    @BeforeEach
    void prepareData() {
        presenter = new AboutBookPresenter(view);
    }

    @Test
    void aboutBookPresenterBundleIsNotEmpty() {
        bundle.putSerializable("book", book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
    }

    @Test
    void aboutBookPresenterBundleIsEmpty() {
        presenter.onStartWithData(bundle);
        verify(view).showErrorMessage();
    }

    @Test
    void aboutBookPresenterViewBundleIsNotEmpty() {
        bundle.putSerializable("book", book);
        view.showBook(book);
        verify(view).showBook(book);
    }
}
