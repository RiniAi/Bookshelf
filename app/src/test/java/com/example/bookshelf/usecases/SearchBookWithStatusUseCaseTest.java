package com.example.bookshelf.usecases;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.book.BookStorage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.example.bookshelf.database.book.Book.BookStatus.PLAN_READING;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchBookWithStatusUseCaseTest {
    @InjectMocks
    SearchBookWithStatusUseCase useCase;
    @Mock
    BookStorage storage;

    @Test
    void useCaseCorrectDataLoading() {
        List<Book> books = new ArrayList<>();
        when(useCase.run(Book.BookStatus.PLAN_READING)).thenReturn(books);
        useCase.run(Book.BookStatus.PLAN_READING);
        verify(storage).getAllWithStatus(PLAN_READING);
    }
}
