package com.example.bookshelf.features.bookssearch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;

import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private Toolbar toolbar;
    private LinearLayout progressBar;
    private RecyclerView books;
    private EditText query;
    private ImageButton sendQuery;
    @Inject
    BookSearchAdapter bookAdapter;
    @Inject
    SearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter)presenter).setView(this);
        setContentView(R.layout.activity_search);
        initControls();
        setSupportActionBar(toolbar);
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (LinearLayout) findViewById(R.id.bar);
        progressBar.setVisibility(View.GONE);
        query = (EditText) findViewById(R.id.et_query);
        sendQuery = (ImageButton) findViewById(R.id.ib_send_query);

        updateToolbar();
        buildRecyclerView();
        buildButtons();
    }

    private void updateToolbar() {
        toolbar.setTitle(R.string.search_activity_title);
    }

    private void buildButtons() {
        sendQuery.setOnClickListener(view -> {
            presenter.searchBook(query.getText().toString());
            hideKeyboard(SearchActivity.this, view);
            hideList();
        });

        query.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchBook(query.getText().toString());
                hideKeyboard(SearchActivity.this, view);
                hideList();
                return true;
            }
            return false;
        });
    }

    private void buildRecyclerView() {
        books = findViewById(R.id.rv_of_books);
        LinearLayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 2);
        books.setLayoutManager(layoutManager);
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
        bookAdapter.setOnEditClickListener(book -> presenter.editBook(book));
    }

    private void hideList() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        books.setVisibility(View.GONE);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showBooks(List<Book> bookList) {
        progressBar.setVisibility(View.GONE);
        books.setVisibility(View.VISIBLE);
        if (bookList == null) {
            Toast.makeText(SearchActivity.this, "Nothing was found for your request!", Toast.LENGTH_SHORT).show();
        } else {
            bookAdapter.setList(bookList);
        }
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(SearchActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_main:
                presenter.openMain();
                break;

            case R.id.go_to_challenge:
                presenter.openBookChallenge();
                break;
        }
        return true;
    }
}


