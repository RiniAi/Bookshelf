package com.example.bookshelf.usecases;

import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SearchBookWithStatusUseCaseTest {
    @InjectMocks
    SearchBookWithStatusUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);

    @Test
    void loadBookUseCaseCorrectDataLoading() {
        useCase = new SearchBookWithStatusUseCase(storage);

        useCase.run();
        verify(storage).getAllWithStatus(FINISH_READING);
    }
}
