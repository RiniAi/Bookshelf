package com.example.bookshelf.features.bookchallenge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.main.MainActivity;

import java.util.List;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;

public class BookChallengeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    public static final String STORAGE_COUNTER = "counter";
    private TextView counter;
    private TextView number;
    private SharedPreferences sharedPreferences;
    private String count;
    private RecyclerView books;
    private BookChallengeAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_challenge);

        initToolbar();
        loadCounter();
        initSeekBar();
        initRecyclerView();
        loadBooks();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.book_challenge_title);
    }

    private void loadCounter() {
        counter = (TextView) findViewById(R.id.tv_counter_books_challenge);
        number = (TextView) findViewById(R.id.tv_number_books_challenge);
        books = (RecyclerView) findViewById(R.id.rv_book_challenge);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        count = sharedPreferences.getString(STORAGE_COUNTER, "0");
        this.counter.setText(count);
    }

    private void initSeekBar() {
        SeekBar sb;
        sb = (SeekBar) findViewById(R.id.sb_counter_book_challenge);
        sb.setOnSeekBarChangeListener(this);
        sb.setProgress(Integer.parseInt(count));
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(BookChallengeActivity.this, 2);
        books.setLayoutManager(layoutManager);
        bookAdapter = new BookChallengeAdapter(getApplicationContext());
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(BookChallengeActivity.this, AboutBookActivity.class);
                intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
                startActivity(intent);
            }

            @Override
            public void onEditClick(Book book) {

            }
        });
    }

    private void loadBooks() {
        BookStorage storage = new BookStorage();
        List<Book> booksFromDatabase = storage.getAllWithStatus(FINISH_READING);
        bookAdapter.setList(booksFromDatabase);
        number.setText(String.valueOf(booksFromDatabase.size()));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        counter.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORAGE_COUNTER, counter.getText().toString());
        editor.apply();
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}