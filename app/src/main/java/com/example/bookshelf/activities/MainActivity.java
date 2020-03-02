package com.example.bookshelf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.GoogleBooksApiService;
import com.example.bookshelf.R;
import com.example.bookshelf.RetrofitClientInstance;
import com.example.bookshelf.Storage;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookItem;
import com.example.bookshelf.models.Item;
import com.example.bookshelf.room.BookEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Item> bookResult = new ArrayList<>();
    private Storage storage;
    private List<Book> bookEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.main_activity_title);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_books);

        bookRequestFromApi();
        loadBooks();
        buildRecyclerView();
    }

    private void bookRequestFromApi() {
        GoogleBooksApiService service = RetrofitClientInstance.getRetrofitInstance().create(GoogleBooksApiService.class);
        bookEntities = new ArrayList<>();
        Call<BookItem> call = service.getBooks("Pop");
        call.enqueue(new Callback<BookItem>() {
            @Override
            public void onResponse(Call<BookItem> call, Response<BookItem> response) {
                bookResult = response.body().getItems();
                storage = new Storage();
                storage.save(bookResult);
            }

            @Override
            public void onFailure(Call<BookItem> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBooks() {
        Storage storage = new Storage();
        List<BookEntity> booksFromDatabase = storage.getList();
        for (BookEntity bookEntity : booksFromDatabase) {
            Book book = new Book();
            storage.loadBooks(book, bookEntity);
            bookEntities.add(book);
        }
    }

    private void buildRecyclerView() {
        RecyclerView books = (RecyclerView) findViewById(R.id.rv_of_books);
        LinearLayout emptyView = (LinearLayout) findViewById(R.id.ll_empty_main_activity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        BookAdapter bookAdapter = new BookAdapter(getApplicationContext());
        books.setLayoutManager(layoutManager);
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(MainActivity.this, AboutBookActivity.class);
                intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
                startActivity(intent);
            }

            @Override
            public void onEditClick(Book book) {
                Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
                intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
                startActivity(intent);
            }
        });

        if (bookEntities.isEmpty()) {
            books.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);

        } else {
            books.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        bookAdapter.setList(bookEntities);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_book_challenge:
                Intent bookChallenge = new Intent(this, BookChallengeActivity.class);
                startActivity(bookChallenge);
                break;
        }
        return true;
    }
}
