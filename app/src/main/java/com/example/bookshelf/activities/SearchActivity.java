package com.example.bookshelf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.ConnectionDetector;
import com.example.bookshelf.GoogleBooksApiService;
import com.example.bookshelf.R;
import com.example.bookshelf.RetrofitClientInstance;
import com.example.bookshelf.adapters.BookSearchAdapter;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookItem;
import com.example.bookshelf.models.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private List<Item> bookResult = new ArrayList<>();
    private List<Book> bookList;
    private Book book;
    private BookSearchAdapter bookAdapter;
    private String query = "";
    private RecyclerView books;
    private LinearLayout emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.search_title);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        emptyView = (LinearLayout) findViewById(R.id.ll_empty_search_activity);
        books = findViewById(R.id.rv_of_books_search_activity);

        EditText enterQuery = (EditText) findViewById(R.id.et_query_search_activity);
        ImageButton sendQuery = (ImageButton) findViewById(R.id.btn_query_search_activity);
        sendQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = enterQuery.getText().toString();

                buildRecyclerView();
                bookRequestFromApi();
            }

        });
        checkInternet();
    }

    private void checkInternet() {
        ConnectionDetector cd = new ConnectionDetector(SearchActivity.this);
        boolean isInternetPresent = cd.ConnectingToInternet();
        if (isInternetPresent) {
            books.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);

        } else {
            books.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }

    }

    private void buildRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
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

    private void bookRequestFromApi() {
        GoogleBooksApiService service = RetrofitClientInstance.getRetrofitInstance().create(GoogleBooksApiService.class);
        Call<BookItem> call = service.getBooks(query);
        bookList = new ArrayList<>();
        call.enqueue(new Callback<BookItem>() {
            @Override
            public void onResponse(Call<BookItem> call, Response<BookItem> response) {
                if (response.body() == null) {
                    Toast.makeText(SearchActivity.this, "Nothing was found for your request!", Toast.LENGTH_SHORT).show();
                } else {
                    bookResult = response.body().getItems();
                    for (int i = 0; i < bookResult.size(); i++) {
                        book = new Book();

                        book.setAuthors(bookResult.get(i).getVolumeInfo().getAuthors().toString()
                                .replace("[", "")
                                .replace("]", ""));
                        if (bookResult.get(i).getVolumeInfo().getTitle() == null)
                            book.setTitle("");
                        else
                            book.setTitle(bookResult.get(i).getVolumeInfo().getTitle());
                        if (bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail() == null)
                            book.setImageURL("");
                        else
                            book.setImageURL(bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail());
                        if (bookResult.get(i).getVolumeInfo().getAverageRating() == 0)
                            book.setAverageRating(0);
                        else
                            book.setAverageRating(bookResult.get(i).getVolumeInfo().getAverageRating());
                        if (bookResult.get(i).getVolumeInfo().getPublisher() == null)
                            book.setPublisher("");
                        else
                            book.setPublisher(bookResult.get(i).getVolumeInfo().getPublisher());
                        if (bookResult.get(i).getVolumeInfo().getPublishedDate() == null)
                            book.setPublishedDate("");
                        else
                            book.setPublishedDate(bookResult.get(i).getVolumeInfo().getPublishedDate());
                        if (bookResult.get(i).getVolumeInfo().getPageCount() == 0)
                            book.setPageCount(0);
                        else
                            book.setDescription(bookResult.get(i).getVolumeInfo().getDescription());
                        if (bookResult.get(i).getVolumeInfo().getLanguage() == null)
                            book.setLang("");
                        else
                            book.setLang(bookResult.get(i).getVolumeInfo().getLanguage());
                        if (bookResult.get(i).getVolumeInfo().getDescription() == null)
                            book.setDescription("");
                        else
                            book.setDescription(bookResult.get(i).getVolumeInfo().getDescription());
                        bookList.add(book);
                    }
                    bookAdapter.setList(bookList);
                }
            }

            @Override
            public void onFailure(Call<BookItem> call, Throwable t) {
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

