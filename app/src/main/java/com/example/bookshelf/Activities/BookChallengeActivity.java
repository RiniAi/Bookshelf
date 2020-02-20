package com.example.bookshelf.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.App;
import com.example.bookshelf.BookChallengeAdapter;
import com.example.bookshelf.Models.Book;
import com.example.bookshelf.R;
import com.example.bookshelf.Room.BookDao;
import com.example.bookshelf.Room.BookDatabase;
import com.example.bookshelf.Room.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookChallengeActivity extends AppCompatActivity {
    TextView number;
    TextView counter;
    Button editCounter;
    List<Book> bookList;

    SharedPreferences sharedPreferences;
    public static final String STORAGE_COUNTER = "counter";

    private RecyclerView books;
    private BookChallengeAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_challenge);
        books = (RecyclerView) findViewById(R.id.rv_book_challenge);
        counter = (TextView) findViewById(R.id.tv_numbers_books_challenge);
        number = (TextView) findViewById(R.id.tv_number_books_challenge);
        editCounter = (Button) findViewById(R.id.btn_edit_number_books);

        loadNumbersBooksChallenge();
        initRecyclerView();
        loadData();
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

    public void editNumberChallenge(View view) {
        final Dialog dialog = new Dialog(BookChallengeActivity.this);
        dialog.setContentView(R.layout.dialog_book_challenge);
        Button btnSet = (Button) dialog.findViewById(R.id.btn_set_book_challenge);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel_book_challenge);
        final NumberPicker np = (NumberPicker) dialog.findViewById(R.id.number_picker_book_challenge);
        np.setMaxValue(1000);
        np.setMinValue(0);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.setText(String.valueOf(np.getValue()));
                saveNumbersBooksChallenge();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void saveNumbersBooksChallenge() {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORAGE_COUNTER, counter.getText().toString());
        editor.apply();
    }

    private void loadNumbersBooksChallenge() {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String numbersBooks = sharedPreferences.getString(STORAGE_COUNTER, "");
        this.counter.setText(numbersBooks);
    }

    public void goToMainActivity (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
