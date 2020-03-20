package com.example.bookshelf.features.bookchallenge;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.database.Book;

import java.util.List;

public class BookChallengeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, BookChallengeContract.View {
    private TextView counter;
    private BookChallengeAdapter bookAdapter;
    private BookChallengeContract.Presenter presenter;
    private Toolbar toolbar;
    private SeekBar seekBar;
    private RecyclerView books;
    private TextView booksCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_challenge);

        initControls();
        presenter = new BookChallengePresenter(this, this);
        presenter.onStart();
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        counter = (TextView) findViewById(R.id.tv_counter);
        books = (RecyclerView) findViewById(R.id.rv_list);
        booksCount = (TextView) findViewById(R.id.tv_books_count);
        seekBar = (SeekBar) findViewById(R.id.sb_counter);
        seekBar.setOnSeekBarChangeListener(this);
        buildToolbar();
        buildRecyclerView();
    }

    private void buildToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.book_challenge_title);
    }

    private void buildRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(BookChallengeActivity.this, 2);
        books.setLayoutManager(layoutManager);
        bookAdapter = new BookChallengeAdapter(getApplicationContext());
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                presenter.openBook(book);
            }
        });
    }

    @Override
    public void setCounter(String count) {
        counter.setText(count);
    }

    @Override
    public void setProgressBar(int count) {
        seekBar.setProgress(count);
    }

    @Override
    public void showList(List<Book> books) {
        bookAdapter.setList(books);
    }

    @Override
    public void setBooksCount(String size) {
        booksCount.setText(size);
    }

    @Override
    public void setProgressCounter(String count) {
        counter.setText(count);
    }

    @Override
    public void showCounterSavedMessage() {
        Toast.makeText(BookChallengeActivity.this, R.string.book_challenge_save_counter, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        presenter.onProgressChanged(progress);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String count = counter.getText().toString();
        presenter.saveCounter(count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_book_challenge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_main_activity:
                presenter.openMain();
                break;
        }
        return true;
    }
}
