package com.example.bookshelf.features.bookssearch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.database.Book;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private SearchContract.Presenter presenter;
    private Toolbar toolbar;
    private LinearLayout progressBar;
    private BookSearchAdapter bookAdapter;
    private RecyclerView books;
    private EditText enterQuery;
    private ImageButton sendQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initControls();
        presenter = new SearchPresenter(this, this);
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (LinearLayout) findViewById(R.id.bar_search_activity);
        progressBar.setVisibility(View.GONE);
        enterQuery = (EditText) findViewById(R.id.et_query_search_activity);
        sendQuery = (ImageButton) findViewById(R.id.btn_query_search_activity);

        buildToolbar();
        buildRecyclerView();
        buildButtons();
    }

    private void buildToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.search_activity_title);
    }

    private void buildButtons() {
        sendQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildSearch(view);
            }
        });

        enterQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    buildSearch(view);
                    return true;
                }
                return false;
            }
        });
    }

    private void buildRecyclerView() {
        books = findViewById(R.id.rv_of_books_search_activity);
        LinearLayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 2);
        bookAdapter = new BookSearchAdapter(getApplicationContext());
        books.setLayoutManager(layoutManager);
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                presenter.openBook(book);
            }
        });
        bookAdapter.setOnEditClickListener(new BookSearchAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(Book book) {
                presenter.editBook(book);
            }
        });
    }

    private void buildSearch(View view) {
        String query = enterQuery.getText().toString();
        presenter.searchBook(query);
        hideKeyboard(SearchActivity.this, view);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        books.setVisibility(View.GONE);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void unSuccessfulRequest() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(SearchActivity.this, "Nothing was found for your request!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfulRequest(List<Book> bookList) {
        bookAdapter.setList(bookList);
        progressBar.setVisibility(View.GONE);
        books.setVisibility(View.VISIBLE);
    }

    @Override
    public void errorRequest() {
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
