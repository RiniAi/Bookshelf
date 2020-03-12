package com.example.bookshelf.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.GoogleBooksApiService;
import com.example.bookshelf.R;
import com.example.bookshelf.RetrofitClientInstance;
import com.example.bookshelf.Storage;
import com.example.bookshelf.adapters.BookSearchAdapter;
import com.example.bookshelf.models.BooksApiResponse;
import com.example.bookshelf.models.BooksApiResponseItem;
import com.example.bookshelf.room.Book;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookshelf.GoogleBooksApiService.QUERY_COUNTER;

public class SearchActivity extends AppCompatActivity {
    private List<BooksApiResponseItem> bookResult = new ArrayList<>();
    private String query = "";
    private BookSearchAdapter bookAdapter;
    private LinearLayout progressBar;
    private RecyclerView books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        progressBar = (LinearLayout) findViewById(R.id.bar_search_activity);
        progressBar.setVisibility(View.GONE);
        initToolbar();
        buildRecyclerView();
        searchBooks();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.search_activity_title);
    }

    private void searchBooks() {
        EditText enterQuery = (EditText) findViewById(R.id.et_query_search_activity);
        ImageButton sendQuery = (ImageButton) findViewById(R.id.btn_query_search_activity);
        sendQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = enterQuery.getText().toString();
                requestBooksFromApi();
                hideKeyboard(SearchActivity.this, view);
                progressBar.setVisibility(ProgressBar.VISIBLE);
                books.setVisibility(View.GONE);
            }
        });

        enterQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    query = enterQuery.getText().toString();
                    requestBooksFromApi();
                    hideKeyboard(SearchActivity.this, view);
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    books.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void buildRecyclerView() {
        books = findViewById(R.id.rv_of_books_search_activity);
        LinearLayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 2);
        bookAdapter = new BookSearchAdapter(getApplicationContext());
        books.setLayoutManager(layoutManager);
        books.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(SearchActivity.this, AboutBookActivity.class);
                intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
                startActivity(intent);
            }

            @Override
            public void onEditClick(Book book) {
                Intent intent = new Intent(SearchActivity.this, EditBookActivity.class);
                intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
                startActivity(intent);
            }
        });
    }

    private void requestBooksFromApi() {
        GoogleBooksApiService service = RetrofitClientInstance.getRetrofitInstance().create(GoogleBooksApiService.class);
        Call<BooksApiResponse> call = service.getBooks(query, QUERY_COUNTER);
        call.enqueue(new Callback<BooksApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<BooksApiResponse> call, @NotNull Response<BooksApiResponse> response) {
                if (response.body() == null) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SearchActivity.this, "Nothing was found for your request!", Toast.LENGTH_SHORT).show();
                } else {
                    bookResult = response.body().getItems();
                    Storage storage = new Storage();
                    progressBar.setVisibility(View.GONE);
                    books.setVisibility(View.VISIBLE);
                    List<Book> bookList = storage.mapResponseDomain(bookResult);
                    bookAdapter.setList(bookList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BooksApiResponse> call, @NotNull Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(SearchActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_main:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                break;

            case R.id.go_to_challenge:
                Intent challenge = new Intent(this, BookChallengeActivity.class);
                startActivity(challenge);
                break;
        }
        return true;
    }
}
