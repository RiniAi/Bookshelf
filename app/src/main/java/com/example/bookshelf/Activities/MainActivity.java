package com.example.bookshelf.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.App;
import com.example.bookshelf.BookAdapter;
import com.example.bookshelf.GetDataService;
import com.example.bookshelf.Models.Book;
import com.example.bookshelf.Models.BookItem;
import com.example.bookshelf.Models.Item;
import com.example.bookshelf.R;
import com.example.bookshelf.RetrofitClientInstance;
import com.example.bookshelf.Room.BookDao;
import com.example.bookshelf.Room.BookDatabase;
import com.example.bookshelf.Room.BookEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Item> bookResult = new ArrayList<>();
    List<Book> bookList = new ArrayList<>();
    Book book;

    private RecyclerView books;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_of_books);
        books = findViewById(R.id.rv_of_books);
        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        books.setLayoutManager(gridLayoutManager);

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

                    bookList.add(book);

                    BookEntity bookEntity = new BookEntity();
                    bookEntity.authors = book.getAuthors();
                    bookEntity.title = book.getTitle();
                    bookEntity.imageLinks = book.getImageURL();
                    bookEntity.averageRating = book.getAverageRating();

                    bookDao.insertBookEntity(bookEntity);

                }
                bookAdapter = new BookAdapter(getApplicationContext(), bookList);
                books.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<BookItem> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
