package com.example.bookshelf.features.main;

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
import com.example.bookshelf.database.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private RecyclerView books;
    private LinearLayout emptyView;
    private BookAdapter bookAdapter;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_books);

        initToolbar();
        buildRecyclerView();
        presenter = new MainPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadBooks();
    }

    @Override
    public void hideList() {
        books.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList(List<Book> booksList) {
        books.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        bookAdapter.setList(booksList);
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_activity_title);
    }

    @Override
    public void buildRecyclerView() {
        books = (RecyclerView) findViewById(R.id.rv_of_books);
        emptyView = (LinearLayout) findViewById(R.id.ll_empty_main_activity);
        bookAdapter = new BookAdapter(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        books.setLayoutManager(layoutManager);
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                presenter.openBook(book);
            }

            @Override
            public void onEditClick(Book book) {
                presenter.editBook(book);
            }
        });
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
                presenter.openBookChallenge();
                break;
            case R.id.go_to_search:
                presenter.openSearchActivity();
                break;
        }
        return true;
    }
}
