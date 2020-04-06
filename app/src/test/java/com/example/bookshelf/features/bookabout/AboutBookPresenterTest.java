package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AboutBookPresenterTest {
    @InjectMocks
    AboutBookPresenter presenter;
    @Mock
    AboutBookContract.View view;
    @Mock
    Bundle bundle;

    private Book book;

    @BeforeEach
    void createBook(){
        presenter = new AboutBookPresenter();
        book = new Book();
        book.setTitle("Чужак");
        book.setAuthors("Фрай");
    }

    @Test
    void onStartWithData() {
        bundle.putSerializable("book", book);
        presenter.onStartWithData(bundle);

        assertEquals(book, bundle);
    }
}
