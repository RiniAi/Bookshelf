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

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private Toolbar toolbar;
    private RecyclerView books;
    private LinearLayout emptyView;
    @Inject
    BookAdapter bookAdapter;
    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter)presenter).setView(this);
        setContentView(R.layout.activity_list_of_books);
        initControls();
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onStart();
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        books = (RecyclerView) findViewById(R.id.rv_list);
        emptyView = (LinearLayout) findViewById(R.id.ll_empty_list);
        updateToolbar();
        buildRecyclerView();
    }

    private void updateToolbar() {
        toolbar.setTitle(R.string.main_activity_title);
    }

    private void buildRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        books.setLayoutManager(layoutManager);
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                presenter.openBook(book);
            }
        });
        bookAdapter.setOnEditClickListener(new BookAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(Book book) {
                presenter.editBook(book);
            }
        });
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
                presenter.openSearch();
                break;
        }
        return true;
    }
}
