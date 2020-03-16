package com.example.bookshelf.activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Storage;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.room.Book;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private Intent intent;
    private MainContract.View context;
    private RecyclerView books;
    private LinearLayout emptyView;
    private BookAdapter bookAdapter;

    public MainPresenter(MainContract.View context, RecyclerView books, LinearLayout emptyView,
                         BookAdapter bookAdapter) {
        this.context = context;
        this.books = books;
        this.emptyView = emptyView;
        this.bookAdapter = bookAdapter;
    }

    @Override
    public void onItemClick(Book book) {
        intent = new Intent((Context) context, AboutBookActivity.class);
        intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        ((Context) context).startActivity(intent);
    }

    @Override
    public void onEditClick(Book book) {
        intent = new Intent((Context) context, EditBookActivity.class);
        intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
        ((Context) context).startActivity(intent);
    }

    @Override
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

    @Override
    public void goToBookChallenge() {
        Intent bookChallenge = new Intent((Context) context, BookChallengeActivity.class);
        ((Context) context).startActivity(bookChallenge);
    }

    @Override
    public void goToSearchActivity() {
        Intent search = new Intent((Context) context, SearchActivity.class);
        ((Context) context).startActivity(search);
    }
}
