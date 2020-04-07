package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.usecases.DeleteBookUseCase.Params;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteBookUseCaseTest {
    private BookStorage storage = mock(BookStorage.class);
    private Book book;
    private Params params;

    @BeforeEach
    void prepareData() {
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
        storage.delete(params.getBook());
        verify(storage).delete(book);
    }

    @Test
    public void deleteBookUseCaseDeleteNull() {
        params = new Params(null);
        storage.delete(params.getBook());
        verify(storage).delete(null);
    }
}
