package com.example.bookshelf.usecases;

import com.example.bookshelf.features.bookssearch.Repository;
import com.example.bookshelf.features.bookssearch.SearchCall.ResponseListener;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RequestBooksUseCaseTest {
    @InjectMocks
    RequestBooksUseCase useCase;
    private Repository repository = mock(Repository.class);
    private ResponseListener listener = mock(ResponseListener.class);

    @Test
    void requestBooksUseCaseRepositoryCorrectCall() {
        useCase = new RequestBooksUseCase(repository);
        useCase.run("Tom", listener);
        verify(repository).requestBooksFromApi("Tom", listener);
    }
}
