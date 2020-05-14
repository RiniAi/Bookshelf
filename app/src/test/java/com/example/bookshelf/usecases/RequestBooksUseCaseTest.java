package com.example.bookshelf.usecases;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.features.bookssearch.Repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestBooksUseCaseTest {
    @InjectMocks
    RequestBooksUseCase useCase;
    @Mock
    Repository repository;

    @Test
    void useCaseReturnsBooksOnSuccess() {
        List<Book> list = new ArrayList<>();
        RequestBooksUseCase.Params params = new RequestBooksUseCase.Params("Tom");
        when(repository.getBooks(params.getQuery())).thenReturn(Single.just(list));
        useCase.run(params)
                .test()
                .assertNoErrors()
                .assertValues(list);
    }

    @Test
    void useCaseReturnsBooksOnError() {
        RequestBooksUseCase.Params params = new RequestBooksUseCase.Params("");
        when(repository.getBooks(params.getQuery())).thenReturn(Single.error(new RuntimeException()));
        useCase.run(params)
                .test()
                .assertError(RuntimeException.class)
                .assertNoValues();
    }
}
