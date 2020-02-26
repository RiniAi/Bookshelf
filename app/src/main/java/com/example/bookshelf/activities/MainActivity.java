package com.example.bookshelf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.App;
import com.example.bookshelf.GetDataService;
import com.example.bookshelf.R;
import com.example.bookshelf.RetrofitClientInstance;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookItem;
import com.example.bookshelf.models.Item;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;
import com.example.bookshelf.room.BookEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Item> bookResult = new ArrayList<>();
    List<Book> bookList = new ArrayList<>();
    List<BookEntity> bookListDao = new ArrayList<>();
    Book book;

    private BookAdapter bookAdapter;

    public static final int ABOUT_BOOK_REQUEST = 1;
    public static final int EDIT_BOOK_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_books);

        buildRecyclerView();
        loadSaveData();
    }

    private void buildRecyclerView() {
        RecyclerView books = findViewById(R.id.rv_of_books);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        bookAdapter = new BookAdapter(getApplicationContext());

        books.setLayoutManager(gridLayoutManager);
        books.setAdapter(bookAdapter);

        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                // TODO anna 26.02.2020: in the rough draft lies data transmission through intention in AboutBookActivity
            }

            @Override
            public void onEditClick(Book book) {
                Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(EditBookActivity.EXTRA_BOOK, book);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_BOOK_REQUEST);
            }
        });
    }

    private void loadSaveData() {
        BookDatabase db = App.getInstance().getDatabase();
        final BookDao bookDao = db.bookDao();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<BookItem> call = service.getData();
        call.enqueue(new Callback<BookItem>() {
            @Override
            public void onResponse(Call<BookItem> call, Response<BookItem> response) {
                bookResult = response.body().getItems();

                for (int i = 0; i < bookResult.size(); i++) {
                    book = new Book();
                    book.setAuthors(bookResult.get(i).getVolumeInfo().getAuthors().toString()
                            .replace("[", "")
                            .replace("]", ""));
                    book.setTitle(bookResult.get(i).getVolumeInfo().getTitle());
                    book.setImageURL(bookResult.get(i).getVolumeInfo().getImageLinks().getThumbnail());
                    book.setAverageRating(bookResult.get(i).getVolumeInfo().getAverageRating());
                    book.setDescription(bookResult.get(i).getVolumeInfo().getDescription());
                    bookList.add(book);

                    BookEntity bookEntity = new BookEntity();
                    bookEntity.authors = book.getAuthors();
                    bookEntity.title = book.getTitle();
                    bookEntity.imageLinks = book.getImageURL();
                    bookEntity.averageRating = book.getAverageRating();
                    bookEntity.description = book.getDescription();
                    bookListDao.add(bookEntity);
                }
                bookAdapter.setList(bookList);
                bookDao.insert(bookListDao);
            }

            @Override
            public void onFailure(Call<BookItem> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToBookChallenge(View view) {
        Intent intent = new Intent(this, BookChallengeActivity.class);
        startActivity(intent);
    }
}
