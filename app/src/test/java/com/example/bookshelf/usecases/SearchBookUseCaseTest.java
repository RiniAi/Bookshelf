package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SearchBookUseCaseTest {
    private BookStorage storage = mock(BookStorage.class);
    private Book book;
    private DeleteBookUseCase.Params params;

    @BeforeEach
    void prepareData() {
        book = new Book();
        book.setAuthors("Фрай");
        book.setTitle("Чужак");
    }

    @Test
    public void searchBookUseCaseParamsReturnsBookCorrectly() {
        params = new DeleteBookUseCase.Params(book);
        assertEquals(book, params.getBook());
    }

    @Test
    public void searchBookUseCaseSearchBook() {
        params = new DeleteBookUseCase.Params(book);
        storage.search(params.getBook());
        verify(storage).search(book);
    }

    @Test
    public void searchBookUseCaseCaseSearchNull() {
        params = new DeleteBookUseCase.Params(null);
        storage.search(params.getBook());
        verify(storage).search(null);
    }
}
