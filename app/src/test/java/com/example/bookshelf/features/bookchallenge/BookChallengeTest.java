package com.example.bookshelf.features.bookchallenge;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.features.usecases.SearchBookWithStatusUseCase;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class BookChallengeTest {
    @InjectMocks
    BookChallengePresenter presenter;
    @Mock
    SearchBookWithStatusUseCase useCase;
    @Mock
    BookChallengeContract.View view;
    @Mock
    BookStorage storage;

    @Test
    public void whenListBooksNotEmpty(){
        useCase = new SearchBookWithStatusUseCase();
        List<Book> books = new ArrayList<>();
        Mockito.when(useCase.run()).thenReturn(books);
        Mockito.verify(storage.getAllWithStatus(Book.BookStatus.FINISH_READING));
    }
}
