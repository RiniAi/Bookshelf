package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void aboutBookPresenterBundleIsNotEmptyAndCoverIsEmpty() {
        when(bundle.containsKey(AboutBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(AboutBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showBookBrokenCover();
    }

    @Test
    void aboutBookPresenterBundleIsNotEmptyAndCoverIsBotEmpty() {
        book.setImageLinks("https://www.rd.com/wp-content/uploads/2019/11/shutterstock_509582812-e1574100998595.jpg");
        when(bundle.containsKey(AboutBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(AboutBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showBookCover(book.getImageLinks());
    }

    @Test
    void aboutBookPresenterBundleIsEmpty() {
        presenter.onStartWithData(bundle);
        verify(view).showErrorMessage();
    }
}
