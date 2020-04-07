package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.usecases.InsertOrUpdateBookUseCase.Params;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InsertOrUpdateBookUseCaseTest {
    @InjectMocks
    InsertOrUpdateBookUseCase useCase;
    private BookStorage storage = mock(BookStorage.class);
    private Book book;
    private Params params;
    private float rating;
    private String status;
    private boolean isFavorite;
    private String date;

    @BeforeEach
    void prepareData() {
        useCase = new InsertOrUpdateBookUseCase(storage);

        book = new Book();
        book.setAuthors("Фрай");
        book.setTitle("Чужак");

        rating = 5;
        status = BookStatusConverter.fromStatusToString(Book.BookStatus.FINISH_READING);
        isFavorite = true;
        date = "02.02.2020";
    }

    @Test
    public void insertOrUpdateBookUseCaseParamsReturnsBookCorrectly() {
        params = new Params(book, rating, status, isFavorite, date);
        assertEquals(book, params.getBook());
    }

    @Test
    public void insertOrUpdateBookUseCaseInsertOrUpdateBook() {
        params = new Params(book, rating, status, isFavorite, date);
        useCase.run(params);
        verify(storage).insertOrUpdate(params.getBook());
    }

    @Test
    public void insertOrUpdateBookUseCaseInsertOrUpdateNull() {
        try {
            params = new Params(null, rating, status, isFavorite, date);
            useCase.run(params);
            verify(storage).insertOrUpdate(params.getBook());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
