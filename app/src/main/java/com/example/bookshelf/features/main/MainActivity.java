package com.example.bookshelf.features.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.databinding.ActivityListOfBooksBinding;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private Toolbar toolbar;
    private ActivityListOfBooksBinding binding;
    @Inject
    BookAdapter bookAdapter;
    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = ActivityListOfBooksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        updateToolbar();
        setSupportActionBar(toolbar);
        buildRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onStart();
    }

    private void updateToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.main_activity_title);
    }

    private void buildRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.rvBooks.setLayoutManager(layoutManager);
        binding.rvBooks.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
        bookAdapter.setOnEditClickListener(book -> presenter.editBook(book));
    }

    @Override
    public void hideList() {
        binding.rvBooks.setVisibility(View.GONE);
        binding.llEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList(List<Book> booksList) {
        binding.rvBooks.setVisibility(View.VISIBLE);
        binding.llEmptyList.setVisibility(View.GONE);
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
