package com.example.bookshelf.features.bookedit;

import android.content.Context;
import android.os.Bundle;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.SearchBookUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class EditBookPresenterTest {
    @InjectMocks
    EditBookPresenter presenter;
    private SearchBookUseCase useCase = mock(SearchBookUseCase.class);
    private EditBookContract.View view = mock(EditBookContract.View.class);
    private Context context = mock(Context.class);
    private Bundle bundle = mock(Bundle.class);
    private Book book = new Book();

    @BeforeEach
    void prepareData() {
        presenter = new EditBookPresenter(useCase, view, context);
    }

    @Test
    void editBookPresenterLoadBookBundleIsEmpty() {
        presenter.onStartWithData(bundle);
        verify(view).showErrorMessage();
    }

    @Test
    void editBookPresenterLoadBookBundleIsNotEmptyAndEmptyCoverEmptyStatus() {
        when(bundle.containsKey(EditBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(EditBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showBrokenCover();
        verify(view).hideButtonDelete();
    }

    @Test
    void editBookPresenterLoadBookBundleIsNotEmptyAndNotEmptyCoverNotEmptyStatus() {
        book.setImageLinks("https://www.rd.com/wp-content/uploads/2019/11/shutterstock_509582812-e1574100998595.jpg");
        book.setStatus(Book.BookStatus.PLAN_READING);
        when(bundle.containsKey(EditBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(EditBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showCover(book.getImageLinks());
        verify(view).showStatus(book.getStatus());
        verify(view).hideButtonDelete();
    }
}
