package com.example.bookshelf.features.listofbooks;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.features.bookabout.AboutBookFragment;

import java.util.List;

public interface BookStatusContract {
    interface View {
        void hideList();

        void showList(List<Book> books);

        void openBook(AboutBookFragment newInstance);
    }

    interface Presenter {
        void onStart(Book.BookStatus status);

        void openBook(Book book);

        void editBook(Book book);
    }
}
