package com.example.bookshelf.features.search;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookssearch.SearchContract;
import com.example.bookshelf.features.bookssearch.SearchPresenter;
import com.example.bookshelf.features.usecases.RequestBooksUseCaseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenterTest {
    @InjectMocks
    SearchPresenter presenter;
    @Mock
    RequestBooksUseCaseTest useCase;
    @Mock
    SearchContract.View view;

    @Mock
    private List<Book> books;
    private Book book;
    private String query;

    @BeforeEach
    void createBook() {
        book = new Book();
        books = new ArrayList<>();
        query = "Tom";
    }

    @Test
    void searchPresenterSendSomeQuery() {
        presenter.searchBook(query);
        Mockito.verify(view).showBooks(books);
    }
}
