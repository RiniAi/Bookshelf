package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchBookWithStatusUseCaseTest {
    private SearchBookWithStatusUseCase useCase = mock(SearchBookWithStatusUseCase.class);
    private BookStorage storage = mock(BookStorage.class);

    @Test
    public void loadBookUseCaseCorrectDataLoading() {
        List<Book> books = new ArrayList<>();
        when(useCase.run()).thenReturn(books);
    }

    @Test
    public void loadBookUseCaseStorageCorrectDataLoading() {
        storage.getAllWithStatus(FINISH_READING);
        verify(storage).getAllWithStatus(FINISH_READING);
    }
}
