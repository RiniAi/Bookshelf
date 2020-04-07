package com.example.bookshelf.usecases;

import com.example.bookshelf.features.bookssearch.Repository;
import com.example.bookshelf.features.bookssearch.SearchCall.ResponseListener;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RequestBooksUseCaseTest {
    private Repository repository = mock(Repository.class);
    private ResponseListener listener = mock(ResponseListener.class);

    @Test
    public void requestBooksUseCaseRepositoryCorrectCall() {
        repository.requestBooksFromApi("Tom", listener);
        verify(repository).requestBooksFromApi("Tom", listener);
    }
}
