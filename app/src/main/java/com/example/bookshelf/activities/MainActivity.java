package com.example.bookshelf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.Storage;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.room.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Storage storage = new Storage();
    private RecyclerView books;
    private LinearLayout emptyView;
    private BookAdapter bookAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_books);

        initToolbar();
        buildRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_activity_title);
    }

    private void buildRecyclerView() {
        books = (RecyclerView) findViewById(R.id.rv_of_books);
        emptyView = (LinearLayout) findViewById(R.id.ll_empty_main_activity);

        mainPresenter = new MainPresenter(this);
        bookAdapter = new BookAdapter(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        books.setLayoutManager(layoutManager);
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                mainPresenter.onItemClick(book);
            }

            @Override
            public void onEditClick(Book book) {
                mainPresenter.onEditClick(book);
            }
        });
    }

    private void loadBooks() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_book_challenge:
                Intent bookChallenge = new Intent(this, BookChallengeActivity.class);
                startActivity(bookChallenge);
                break;
            case R.id.go_to_search:
                Intent search = new Intent(this, SearchActivity.class);
                startActivity(search);
                break;
        }
        return true;
    }
}
