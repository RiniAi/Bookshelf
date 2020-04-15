package com.example.bookshelf.usecases;

import com.example.bookshelf.features.bookssearch.Repository;
import com.example.bookshelf.features.bookssearch.SearchCall.ResponseListener;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestBooksUseCaseTest {
    @InjectMocks
    RequestBooksUseCase useCase;
    @Mock
    Repository repository;
    @Mock
    ResponseListener listener;

    @Test
    void requestBooksUseCaseRepositoryCorrectCall() {
        useCase.run("Tom", listener);
        verify(repository).requestBooksFromApi("Tom", listener);
    }
}
