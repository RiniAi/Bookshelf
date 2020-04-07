package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoadBookUseCaseTest {
    private LoadBookUseCase useCase = mock(LoadBookUseCase.class);
    private BookStorage storage = mock(BookStorage.class);

    @Test
    public void loadBookUseCaseCorrectDataLoading() {
        List<Book> books = new ArrayList<>();
        when(useCase.run()).thenReturn(books);
    }

    @Test
    public void loadBookUseCaseStorageCorrectDataLoading() {
        storage.getAll();
        verify(storage).getAll();
    }
}
