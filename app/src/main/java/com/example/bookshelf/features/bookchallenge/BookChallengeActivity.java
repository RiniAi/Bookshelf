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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_challenge);

        presenter = new BookChallengePresenter(this, this);
        initToolbar();
        presenter.loadCounter();
        initRecyclerView();
        presenter.loadBooks();
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.book_challenge_title);
    }

    @Override
    public void initCounter(String count) {
        counter = (TextView) findViewById(R.id.tv_counter_books_challenge);
        counter.setText(count);
    }

    @Override
    public void initSeekBar(int count) {
        SeekBar sb = (SeekBar) findViewById(R.id.sb_counter_book_challenge);
        sb.setOnSeekBarChangeListener(this);
        sb.setProgress(count);
    }

    @Override
    public void initRecyclerView() {
        RecyclerView books = (RecyclerView) findViewById(R.id.rv_book_challenge);
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
    public void showList(List<Book> books, String size) {
        bookAdapter.setList(books);
        TextView number = (TextView) findViewById(R.id.tv_number_books_challenge);
        number.setText(size);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        presenter.convertCount(seekBar);
    }

    @Override
    public void setProgress(String count) {
        counter.setText(count);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String count = counter.getText().toString();
        presenter.saveCount(seekBar, count);
        Toast.makeText(BookChallengeActivity.this, R.string.book_challenge_save_counter, Toast.LENGTH_SHORT).show();
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
