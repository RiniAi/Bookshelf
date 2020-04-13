package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchBookWithStatusUseCaseTest {
    @InjectMocks
    SearchBookWithStatusUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);
    private List<Book> books;

    @BeforeEach
    void prepareData() {
        useCase = new SearchBookWithStatusUseCase(storage);
        books = new ArrayList<>();
    }

    @Test
    void loadBookUseCaseCorrectDataLoading() {
        when(useCase.run()).thenReturn(books);
        useCase.run();
        verify(storage).getAllWithStatus(FINISH_READING);
    }
}
