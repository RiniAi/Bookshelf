package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.usecases.SearchBookUseCase.Params;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchBookUseCaseTest {
    @InjectMocks
    SearchBookUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);
    private Book book = new Book();
    private Params params;

    @BeforeEach
    void prepareData() {
        useCase = new SearchBookUseCase(storage);
    }

    @Test
    void searchBookUseCaseParamsReturnsBookCorrectly() {
        params = new Params(book);
        assertEquals(book, params.getBook());
    }

    @Test
    void searchBookUseCaseSearchBook() {
        params = new Params(book);
        useCase.run(params);
        when(useCase.run(params)).thenReturn(book);
        verify(storage).search(params.getBook());
    }

    @Test
    void searchBookUseCaseCaseSearchNull() {
        params = new Params(null);
        useCase.run(params);
        when(useCase.run(params)).thenReturn(null);
    }
}
