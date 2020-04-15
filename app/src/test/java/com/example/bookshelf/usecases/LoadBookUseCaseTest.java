package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadBookUseCaseTest {
    @InjectMocks
    LoadBookUseCase useCase;
    @Mock
    BookStorage storage;

    @Test
    void loadBookUseCaseCorrectDataLoading() {
        List<Book> books = new ArrayList<>();
        when(useCase.run()).thenReturn(books);
        useCase.run();
        verify(storage).getAll();
    }
}
