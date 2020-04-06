package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class InsertOrUpdateBookUseCaseTest {
    @InjectMocks
    InsertOrUpdateBookUseCase useCase;
    @Mock
    BookStorage storage;

    @Test
    public void insertOrUpdateBookUseCaseDetermineWhetherToAddBookOrUpdateDataForIt(){
        Book book = new Book();
        float rating = 5;
        String status = BookStatusConverter.fromStatusToString(Book.BookStatus.FINISH_READING);
        boolean isFavorite = true;
        String date = "02.02.2020";
        InsertOrUpdateBookUseCase.Params params = new InsertOrUpdateBookUseCase.Params(book, rating, status, isFavorite, date);

    }
}
