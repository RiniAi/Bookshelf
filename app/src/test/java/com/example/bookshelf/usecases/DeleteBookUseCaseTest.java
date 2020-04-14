package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.usecases.DeleteBookUseCase.Params;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteBookUseCaseTest {
    @InjectMocks
    DeleteBookUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);
    private Book book = new Book();

    @BeforeEach
    void prepareData() {
        useCase = new DeleteBookUseCase(storage);
    }

    @Test
    void deleteBookUseCaseDeleteBook() {
        Params params = new Params(book);
        useCase.run(params);
        verify(storage).delete(params.getBook());
    }

    @Test
    void deleteBookUseCaseDeleteNull() {
        Params params = new Params(null);
        useCase.run(params);
        verify(storage).delete(params.getBook());
    }
}
