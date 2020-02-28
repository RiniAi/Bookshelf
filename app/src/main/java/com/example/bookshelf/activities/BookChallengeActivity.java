package com.example.bookshelf.activities;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.adapters.BookChallengeAdapter;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;
import com.example.bookshelf.room.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookChallengeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView counter;
    private TextView number;
    private SharedPreferences sharedPreferences;
    private String count;
    public static final String STORAGE_COUNTER = "counter";

    private RecyclerView books;
    private BookChallengeAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.title_book_challenge);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_challenge);

        loadCounter();
        initSeekBar();
        initRecyclerView();
        loadData();
    }

    private void loadCounter() {
        counter = (TextView) findViewById(R.id.tv_counter_books_challenge);
        number = (TextView) findViewById(R.id.tv_number_books_challenge);
        books = (RecyclerView) findViewById(R.id.rv_book_challenge);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        count = sharedPreferences.getString(STORAGE_COUNTER, "");
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
    }

    private void loadData() {
        BookDatabase db = App.getInstance().getDatabase();
        final BookDao bookDao = db.bookDao();
        List<BookEntity> booksFromDatabase = bookDao.getList();
        List<Book> bookEntities = new ArrayList<>();
        for (BookEntity bookEntity : booksFromDatabase) {
            Book book = new Book();
            book.setTitle(bookEntity.getTitle());
            book.setAuthors(bookEntity.getAuthors());
            book.setAverageRating(bookEntity.getAverageRating());
            book.setImageURL(bookEntity.getImageLinks());
            bookEntities.add(book);
        }
        bookAdapter.setList(bookEntities);
        number.setText(String.valueOf(bookEntities.size()));
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
        Toast.makeText(BookChallengeActivity.this, R.string.save_counter_book_challenge, Toast.LENGTH_SHORT).show();
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
        }
        return true;
    }
}
