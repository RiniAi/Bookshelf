package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.Book;

import java.util.List;

public interface SearchCall {
    interface onSuccessfulCall {
        void getBooks(List<Book> books);
    }

    interface onUnSuccessfulCall {
        void getThrowable(Throwable t);
    }
}
