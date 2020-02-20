package com.example.bookshelf.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Adapters.BookChallengeAdapter;
import com.example.bookshelf.App;
import com.example.bookshelf.Models.Book;
import com.example.bookshelf.R;
import com.example.bookshelf.Room.BookDao;
import com.example.bookshelf.Room.BookDatabase;
import com.example.bookshelf.Room.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookChallengeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    TextView counter;
    TextView number;
    Button editCounter;
    List<Book> bookList;

    SeekBar sb;
    SharedPreferences sharedPreferences;
    public static final String STORAGE_COUNTER = "counter";

    private RecyclerView books;
    private BookChallengeAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_challenge);
        counter = (TextView) findViewById(R.id.tv_counter_books_challenge);
        number = (TextView) findViewById(R.id.tv_number_books_challenge);
        books = (RecyclerView) findViewById(R.id.rv_book_challenge);
        editCounter = (Button) findViewById(R.id.btn_edit_number_books);

        initSeekBar();
        loadCounterNumber();
        initRecyclerView();
        loadData();
    }

    private void initSeekBar() {
        sb = new SeekBar(BookChallengeActivity.this);
        sb = (SeekBar) findViewById(R.id.sb_counter_book_challenge);
        sb.setOnSeekBarChangeListener(this);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookChallengeActivity.this);
        books.setLayoutManager(linearLayoutManager);
        bookAdapter = new BookChallengeAdapter(getApplicationContext());
        books.setAdapter(bookAdapter);
    }

    private void loadData() {
        BookDatabase db = App.getInstance().getDatabase();
        final BookDao bookDao = db.bookDao();
        List<BookEntity> booksFromDatabase = bookDao.getList();

        bookList = new ArrayList<>();
        for (BookEntity bookEntity : booksFromDatabase) {
            Book book = new Book();
            book.setTitle(bookEntity.getTitle());
            book.setAuthors(bookEntity.getAuthors());
            book.setAverageRating(bookEntity.getAverageRating());

            bookList.add(book);
        }
        bookAdapter.setList(bookList);
        number.setText(String.valueOf(bookList.size()));
    }

    public void saveCounterNumber(View view) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORAGE_COUNTER, counter.getText().toString());
        editor.apply();
    }

    private void loadCounterNumber() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String counter = sharedPreferences.getString(STORAGE_COUNTER, "");
        this.counter.setText(counter);
    }

    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
    }
}
