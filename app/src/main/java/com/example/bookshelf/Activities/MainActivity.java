package com.example.bookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookshelf.BookAdapter;
import com.example.bookshelf.Models.Book;
import com.example.bookshelf.R;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView employeesList;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter bookAdapter;
    private ArrayList<Book> bookArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_of_books);

        initRecyclerView();
        loadBooks();
    }

    private void initRecyclerView() {
        employeesList = findViewById(R.id.rv_of_books);
        employeesList.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        employeesList.setLayoutManager(gridLayoutManager);

        bookAdapter = new BookAdapter(getApplicationContext(), bookArrayList);
        employeesList.setAdapter(bookAdapter);
    }

    private void loadBooks() {
        Book book = new Book();

        book.setAuthors("Nicholas Eames");
        book.setTitle("Kings of the Wyld");
        book.setImage(R.drawable.ic_launcher_background);
        book.setAverageRating(10);

        bookArrayList.addAll(Collections.nCopies(50, book));

    }
}
