package com.example.bookshelf.features.listofbooks;

import com.example.bookshelf.database.Book;

import java.util.List;

public interface ListOfBooksContract {
    interface View {
        void hideList();

        void showList(List<Book> books);
    }

    interface Presenter {
        void onStart(Book.BookStatus status);

        void openBook(Book book);

        void editBook(Book book);
    }
}
