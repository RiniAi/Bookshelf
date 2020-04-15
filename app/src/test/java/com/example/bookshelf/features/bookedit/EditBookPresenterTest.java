package com.example.bookshelf.features.bookedit;

import android.content.Context;
import android.os.Bundle;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.DeleteBookUseCase;
import com.example.bookshelf.usecases.InsertOrUpdateBookUseCase;
import com.example.bookshelf.usecases.SearchBookUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditBookPresenterTest {
    @InjectMocks
    EditBookPresenter presenter;
    @Mock
    SearchBookUseCase searchBookUseCase;
    @Mock
    InsertOrUpdateBookUseCase insertOrUpdateBookUseCase;
    @Mock
    DeleteBookUseCase deleteBookUseCase;
    @Mock
    EditBookContract.View view;
    @Mock
    Context context;
    @Mock
    Bundle bundle;

    private Book book;

    @BeforeEach
    void setUp() {
        presenter.setView(view);
        book = new Book();
    }

    @Test
    void editBookPresenterLoadBookBundleIsNotEmptyAndNotEmptyCoverNotEmptyStatusAndBookDbIsNotNull() {
        book.setImageLinks("https://www.rd.com/wp-content/uploads/2019/11/shutterstock_509582812-e1574100998595.jpg");
        book.setStatus(Book.BookStatus.PLAN_READING);
        when(context.getString(anyInt())).thenReturn("");
        when(bundle.containsKey(EditBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(EditBookPresenter.EXTRA_BOOK)).thenReturn(book);
        when(searchBookUseCase.run(isA(SearchBookUseCase.Params.class))).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showCover(book.getImageLinks());
        verify(view).showStatus(book.getStatus());
        verify(searchBookUseCase).run(isA(SearchBookUseCase.Params.class));
        verify(view).showButtonDelete();
    }

    @Test
    void editBookPresenterLoadBookBundleIsNotEmptyAndEmptyCoverEmptyStatusAndBookDbIsNotNull() {
        when(context.getString(anyInt())).thenReturn("");
        when(bundle.containsKey(EditBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(EditBookPresenter.EXTRA_BOOK)).thenReturn(book);
        when(searchBookUseCase.run(isA(SearchBookUseCase.Params.class))).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showBrokenCover();
        verify(searchBookUseCase).run(isA(SearchBookUseCase.Params.class));
        verify(view).showButtonDelete();
    }

    @Test
    void editBookPresenterLoadBookBundleIsNotEmptyAndEmptyCoverEmptyStatusAndBookDbIsNull() {
        when(context.getString(anyInt())).thenReturn("");
        when(bundle.containsKey(EditBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(EditBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        verify(view).showBook(book);
        verify(view).showBrokenCover();
        verify(view).hideButtonDelete();
    }

    @Test
    void editBookPresenterLoadBookBundleIsEmpty() {
        presenter.onStartWithData(null);
        verify(view).showErrorMessage();
    }

    @Test
    void editBookPresenterInsertOrUpdateBook() {
        when(bundle.containsKey(EditBookPresenter.EXTRA_BOOK)).thenReturn(true);
        when(bundle.getSerializable(EditBookPresenter.EXTRA_BOOK)).thenReturn(book);
        presenter.onStartWithData(bundle);
        presenter.insertOrUpdateBook(5, "Plan", true);
        InsertOrUpdateBookUseCase.Params params = new InsertOrUpdateBookUseCase.Params(
                book,
                5,
                "Plan",
                true,
                "20.02.2020"
        );
        verify(view).showDate();
        verify(insertOrUpdateBookUseCase).run(params);
    }

    @Test
    void editBookPresenterDeleteBook() {
        presenter.deleteBook();
        verify(deleteBookUseCase).run(isA(DeleteBookUseCase.Params.class));
    }
}
