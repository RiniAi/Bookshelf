package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.usecases.SearchBookUseCase.Params;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchBookUseCaseTest {
    @InjectMocks
    SearchBookUseCase useCase;
    @Mock
    BookStorage storage;

    @Test
    void useCaseSearchBookToDataBase() {
        Book book = new Book();
        Params params = new Params(book);
        when(useCase.run(params)).thenReturn(book);
        useCase.run(params);
        verify(storage).search(params.getBook());
    }
}
