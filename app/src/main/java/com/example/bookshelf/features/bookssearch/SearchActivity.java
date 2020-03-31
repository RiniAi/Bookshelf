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
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.databinding.ActivitySearchBinding;

import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private Toolbar toolbar;
    private ActivitySearchBinding binding;
    @Inject
    BookSearchAdapter bookAdapter;
    @Inject
    SearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.progressBar.setVisibility(View.GONE);
        updateToolbar();
        buildRecyclerView();
        buildButtons();
        setSupportActionBar(toolbar);
    }

    private void updateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.search_activity_title);
    }

    private void buildButtons() {
        binding.ibSendQuery.setOnClickListener(view -> {
            presenter.searchBook(binding.etQuery.getText().toString());
            hideKeyboard(SearchActivity.this, view);
            hideList();
        });

        binding.etQuery.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchBook(binding.etQuery.getText().toString());
                hideKeyboard(SearchActivity.this, view);
                hideList();
                return true;
            }
            return false;
        });
    }

    private void buildRecyclerView() {
        LinearLayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 2);
        binding.rvOfBooks.setLayoutManager(layoutManager);
        binding.rvOfBooks.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
        bookAdapter.setOnEditClickListener(book -> presenter.editBook(book));
    }

    private void hideList() {
        binding.progressBar.setVisibility(ProgressBar.VISIBLE);
        binding.rvOfBooks.setVisibility(View.GONE);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showBooks(List<Book> bookList) {
        binding.progressBar.setVisibility(View.GONE);
        binding.rvOfBooks.setVisibility(View.VISIBLE);
        if (bookList == null) {
            Toast.makeText(SearchActivity.this, "Nothing was found for your request!", Toast.LENGTH_SHORT).show();
        } else {
            bookAdapter.setList(bookList);
        }
    }

    @Override
    public void showError() {
        binding.progressBar.setVisibility(View.GONE);
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


