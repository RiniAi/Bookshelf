package com.example.bookshelf.features.bookedit;

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
    private SearchBookUseCase.Params searchParams = mock(SearchBookUseCase.Params.class);
    private SearchBookUseCase useCase = mock(SearchBookUseCase.class);
    private EditBookContract.View view = mock(EditBookContract.View.class);
    private Bundle bundle = mock(Bundle.class);
    private Book book = mock(Book.class);

    @BeforeEach
    void prepareData() {
        presenter = new EditBookPresenter(useCase, view);
    }

    @Test
    void editBookPresenterLoadBookBundleIsEmpty() {
        presenter.onStartWithData(bundle);
        verify(view).showErrorMessage();
    }

    @Test
    void editBookPresenterSearchBook() {
        presenter.onStartWithData(bundle);
        useCase.run(searchParams);
        when(useCase.run(searchParams)).thenReturn(book);
        verify(view).hideButtonDelete();
    }
}
