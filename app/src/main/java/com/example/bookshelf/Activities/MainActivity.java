package com.example.bookshelf.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.BookAdapter;
import com.example.bookshelf.Models.BookItem;
import com.example.bookshelf.GetDataService;
import com.example.bookshelf.Models.Item;
import com.example.bookshelf.R;
import com.example.bookshelf.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     ArrayList<Item> bookList = new ArrayList<>();

        private RecyclerView employeesList;
        private GridLayoutManager gridLayoutManager;
        private RecyclerView.Adapter bookAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_list_of_books);

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<BookItem> call = service.getData();
            call.enqueue(new Callback<BookItem>() {
                @Override
                public void onResponse(Call<BookItem> call, Response<BookItem> response) {
                    bookList = response.body().getItems();

                    employeesList = findViewById(R.id.rv_of_books);
                    bookAdapter = new BookAdapter(getApplicationContext(),bookList);
                    gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    employeesList.setLayoutManager(gridLayoutManager);
                    employeesList.setAdapter(bookAdapter);
                }

                @Override
                public void onFailure(Call<BookItem> call, Throwable t) {
                    Log.e("error", t.toString());
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });

        }
}
