package com.example.bookshelf.features.main;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.LoadBookUseCase;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

public class MainPresenterTest {
    @InjectMocks
    MainPresenter presenter;
    @Mock
    LoadBookUseCase loadBookUseCase;
    @Mock
    MainContract.View view;
    @Mock
    Navigator navigator;

    @Test
    public void mainPresenterLoadBooks() {
        List<Book> books = loadBookUseCase.run();
        if (books.isEmpty()) {
            view.hideList();
        } else {
            view.showList(books);
        }
    }
}
