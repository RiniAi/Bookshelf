package com.example.bookshelf.usecases;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.book.BookStatusConverter;
import com.example.bookshelf.database.book.BookStorage;
import com.example.bookshelf.usecases.InsertOrUpdateBookUseCase.Params;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InsertOrUpdateBookUseCaseTest {
    @InjectMocks
    InsertOrUpdateBookUseCase useCase;
    @Mock
    BookStorage storage;

    private float rating;
    private String status;
    private boolean isFavorite;
    private String date;

    @BeforeEach
    void prepareData() {
        rating = 5;
        status = BookStatusConverter.fromStatusToString(Book.BookStatus.FINISH_READING);
        isFavorite = true;
        date = "02.02.2020";
    }

    @Test
    void useCaseInsertOrUpdateBookSuccessfully() {
        Book book = new Book();
        Params params = new Params(book, rating, status, isFavorite, date);
        useCase.run(params);
        verify(storage).insertOrUpdate(params.getBook());
    }

    @Test
    void useCaseInsertOrUpdateNullNotSuccessfully() {
        try {
            Params params = new Params(null, rating, status, isFavorite, date);
            useCase.run(params);
            verify(storage).insertOrUpdate(params.getBook());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
