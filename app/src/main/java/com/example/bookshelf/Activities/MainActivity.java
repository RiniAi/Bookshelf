package com.example.bookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.bookshelf.AdapterBook;
import com.example.bookshelf.Models.Book;
import com.example.bookshelf.R;
import java.util.ArrayList;

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
        getDate();
    }

    private void initRecyclerView() {
        employeesList = findViewById(R.id.rv_of_books);
        employeesList.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        employeesList.setLayoutManager(gridLayoutManager);

        bookAdapter = new AdapterBook(getApplicationContext(), bookArrayList);
        employeesList.setAdapter(bookAdapter);
    }

    private void getDate() {
        bookArrayList.add(new Book(R.drawable.book, "Nicholas Eames", "Kings of the Wyld", 10));

    }
}
