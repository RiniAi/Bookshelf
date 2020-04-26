package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.usecases.DeleteBookUseCase.Params;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteBookUseCaseTest {
    @InjectMocks
    DeleteBookUseCase useCase;
    @Mock
    BookStorage storage;

    @Test
    void useCaseDeleteBook() {
        Book book = new Book();
        Params params = new Params(book);
        useCase.run(params);
        verify(storage).delete(params.getBook());
    }
}
