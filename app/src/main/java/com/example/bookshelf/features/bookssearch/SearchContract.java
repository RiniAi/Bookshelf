package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.features.bookabout.AboutBookFragment;

import java.util.List;

public interface SearchContract {
    interface View {
        void showBooks(List<Book> bookList);

        void showError();

        void showErrorMessage();

        void showEmptyView();

        void openBook(AboutBookFragment newInstance);
    }

    interface Presenter {
        void searchBook(String query, int startIndex);

        void openBook(Book book);

        void editBook(Book book);

        void unsubscribe();
    }
}
