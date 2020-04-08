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

class SearchBookUseCaseTest {
    @InjectMocks
    SearchBookUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);
    private Book book;
    private Params params;

    @BeforeEach
    void prepareData() {
        useCase = new SearchBookUseCase(storage);

        book = new Book();
        book.setAuthors("Фрай");
        book.setTitle("Чужак");
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
        verify(storage).search(params.getBook());
    }

    @Test
    void searchBookUseCaseCaseSearchNull() {
        params = new Params(null);
        useCase.run(params);
        verify(storage).search(params.getBook());
    }
}
