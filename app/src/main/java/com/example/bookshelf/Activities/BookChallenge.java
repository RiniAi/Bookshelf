package com.example.bookshelf.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.App;
import com.example.bookshelf.BookAdapterChallenge;
import com.example.bookshelf.Models.Book;
import com.example.bookshelf.R;
import com.example.bookshelf.Room.BookDao;
import com.example.bookshelf.Room.BookDatabase;
import com.example.bookshelf.Room.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookChallenge extends AppCompatActivity {
    TextView numberBooksChallenge;
    TextView numbersBooksChallenge;
    Button btnEditNumber;
    List<BookEntity> listDatabase;
    List<Book> listBook;

    private RecyclerView books;
    private BookAdapterChallenge bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book_challenge);
        books = (RecyclerView) findViewById(R.id.rv_book_challenge);
        numbersBooksChallenge = (TextView) findViewById(R.id.tv_numbers_books_challenge);
        numberBooksChallenge = (TextView) findViewById(R.id.tv_number_books_challenge);
        btnEditNumber = (Button) findViewById(R.id.btn_edit_number_books);

        initRecyclerView();
        loadData();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookChallenge.this);
        books.setLayoutManager(linearLayoutManager);
        bookAdapter = new BookAdapterChallenge(getApplicationContext());
        books.setAdapter(bookAdapter);
    }

    private void loadData() {
        BookDatabase db = App.getInstance().getDatabase();
        final BookDao bookDao = db.bookDao();
        listDatabase = bookDao.getList();

        listBook = new ArrayList<>();
        for (BookEntity bookEntity : listDatabase) {
            Book book = new Book();
            book.setTitle(bookEntity.getTitle());
            book.setAuthors(bookEntity.getAuthors());
            book.setAverageRating(bookEntity.getAverageRating());

            listBook.add(book);
        }
        bookAdapter.setList(listBook);
        numberBooksChallenge.setText(String.valueOf(listBook.size()));
    }
}
