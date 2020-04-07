package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.usecases.DeleteBookUseCase.Params;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteBookUseCaseTest {
    @InjectMocks
    DeleteBookUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);
    private Book book;
    private Params params;

    @BeforeEach
    void prepareData() {
        useCase = new DeleteBookUseCase(storage);

        book = new Book();
        book.setAuthors("Фрай");
        book.setTitle("Чужак");
    }

    @Test
    public void deleteBookUseCaseParamsReturnsBookCorrectly() {
        params = new Params(book);
        assertEquals(book, params.getBook());
    }

    @Test
    public void deleteBookUseCaseDeleteBook() {
        params = new Params(book);
        useCase.run(params);
        verify(storage).delete(params.getBook());
    }

    @Test
    public void deleteBookUseCaseDeleteNull() {
        params = new Params(null);
        useCase.run(params);
        verify(storage).delete(params.getBook());
    }
}
