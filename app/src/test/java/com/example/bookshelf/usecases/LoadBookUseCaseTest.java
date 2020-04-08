package com.example.bookshelf.usecases;

import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoadBookUseCaseTest {
    @InjectMocks
    LoadBookUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);

    @Test
    void loadBookUseCaseCorrectDataLoading() {
        useCase = new LoadBookUseCase(storage);

        useCase.run();
        verify(storage).getAll();
    }
}
