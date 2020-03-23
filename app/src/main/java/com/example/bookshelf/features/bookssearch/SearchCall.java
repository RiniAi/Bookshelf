package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.Book;

import java.util.List;

public interface SearchCall {
    interface responseListener {
        void onSuccess(List<Book> books);
        void onFailure(Throwable t);

    }
}
