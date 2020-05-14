package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.book.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AboutBookPresenterTest {
    @InjectMocks
    AboutBookPresenter presenter;
    @Mock
    AboutBookContract.View view;
    @Mock
    Bundle bundle;

    private Book book;

    @BeforeEach
    void setUp() {
        presenter.setView(view);
        book = new Book();
    }

    @Test
    void presenterGetBookFromBundleThatIsNotEmptyCoverIsNotEmpty() {
        book.setImageLinks("https://www.rd.com/wp-content/uploads/2019/11/shutterstock_509582812-e1574100998595.jpg");
        when(bundle.containsKey(AboutBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(AboutBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showBookCover(book.getImageLinks());
    }

    @Test
    void presenterGetBookFromBundleThatIsNotEmptyCoverIsEmpty() {
        when(bundle.containsKey(AboutBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(AboutBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showBookBrokenCover();
    }

    @Test
    void presenterGetBookFromBundleThatIsEmpty() {
        presenter.onStartWithData(null);
        verify(view).showErrorMessage();
    }
}
