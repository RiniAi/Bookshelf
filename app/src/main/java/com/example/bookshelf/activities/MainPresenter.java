package com.example.bookshelf.activities;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Storage;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.room.Book;

import java.util.List;

public class MainPresenter {
    private Intent intent;
    private MainActivity mainActivity;
    private RecyclerView books;
    private LinearLayout emptyView;
    private BookAdapter bookAdapter;

    public MainPresenter(MainActivity mainActivity, RecyclerView books, LinearLayout emptyView,
                         BookAdapter bookAdapter) {
        this.mainActivity = mainActivity;
        this.books = books;
        this.emptyView = emptyView;
        this.bookAdapter = bookAdapter;
    }

    public void onItemClick(Book book) {
        intent = new Intent(mainActivity, AboutBookActivity.class);
        intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        mainActivity.startActivity(intent);
    }

    public void onEditClick(Book book) {
        intent = new Intent(mainActivity, EditBookActivity.class);
        intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
        mainActivity.startActivity(intent);
    }

    public void loadBooks() {
        Storage storage = new Storage();
        List<Book> booksFromDatabase = storage.searchForBooksWithStatus();
        if (booksFromDatabase.isEmpty()) {
            books.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            books.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            bookAdapter.setList(booksFromDatabase);
        }
    }
}
