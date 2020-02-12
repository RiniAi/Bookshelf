package com.example.bookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.bookshelf.AdapterBook;
import com.example.bookshelf.Models.Book;
import com.example.bookshelf.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter adapterBook;
    private ArrayList<Book> arrayListBook = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_of_books);

        initRecyclerView();
        getDate();
    }

    private void initRecyclerView() {
        rvList = findViewById(R.id.rv_of_books);
        rvList.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        rvList.setLayoutManager(gridLayoutManager);

        adapterBook = new AdapterBook(getApplicationContext(), arrayListBook);
        rvList.setAdapter(adapterBook);
    }

    private void getDate() {
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
        arrayListBook.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));
    }
}
