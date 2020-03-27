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

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;

import java.util.List;

import javax.inject.Inject;

public class BookChallengeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, BookChallengeContract.View {
    private RecyclerView books;
    private TextView progress;
    private TextView counter;
    private Toolbar toolbar;
    private SeekBar seekBar;
    @Inject
    BookChallengeAdapter bookAdapter;
    @Inject
    BookChallengeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().bookChallengeActivityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        setContentView(R.layout.activity_book_challenge);
        initControls();
        presenter.onStart();
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progress = (TextView) findViewById(R.id.tv_progress);
        counter = (TextView) findViewById(R.id.tv_counter);
        books = (RecyclerView) findViewById(R.id.rv_list);
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
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                presenter.openBook(book);
            }
        });
    }

    @Override
    public void changeCounter(String counter) {
        this.counter.setText(counter);
    }

    @Override
    public void changeCounterForBar(int counter) {
        seekBar.setProgress(counter);
    }

    @Override
    public void showList(List<Book> books) {
        bookAdapter.setList(books);
    }

    @Override
    public void changeProgress(String progress) {
        this.progress.setText(progress);
    }

    @Override
    public void showCounterSavedMessage() {
        Toast.makeText(BookChallengeActivity.this, R.string.book_challenge_save_counter, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int counter, boolean fromUser) {
        presenter.onProgressChanged(counter);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        presenter.saveCounter(counter.getText().toString());
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
