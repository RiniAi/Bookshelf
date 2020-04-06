package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class LoadBookUseCaseTest {
    @InjectMocks
    LoadBookUseCase useCase;
    @Mock
    BookStorage storage;

    @Test
    public void loadBooksUseCaseGetBooksFromStorage() {
        useCase = new LoadBookUseCase();
        try {
            Mockito.when(useCase.run()).thenReturn(storage.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
