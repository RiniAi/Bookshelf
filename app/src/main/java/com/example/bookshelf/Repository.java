package com.example.bookshelf;

import android.util.Log;

import com.example.bookshelf.models.BooksApiResponse;
import com.example.bookshelf.models.BooksApiResponseItem;
import com.example.bookshelf.network.GoogleBooksApiService;
import com.example.bookshelf.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookshelf.features.bookssearch.SearchPresenter.unSuccessfulRequest;
import static com.example.bookshelf.features.bookssearch.SearchPresenter.errorRequest;
import static com.example.bookshelf.features.bookssearch.SearchPresenter.successfulRequest;
import static com.example.bookshelf.network.BookMapper.mapResponseToDomain;
import static com.example.bookshelf.network.GoogleBooksApiService.QUERY_COUNTER;

public class Repository {
    private List<BooksApiResponseItem> bookResult;

    public void requestBooksFromApi(String query) {
        bookResult = new ArrayList<>();
        GoogleBooksApiService service = RetrofitClientInstance.getRetrofitInstance().create(GoogleBooksApiService.class);
        Call<BooksApiResponse> call = service.getBooks(query, QUERY_COUNTER);
        call.enqueue(new Callback<BooksApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<BooksApiResponse> call, @NotNull Response<BooksApiResponse> response) {
                if (response.body() == null) {
                    unSuccessfulRequest();
                } else {
                    bookResult = response.body().getItems();
                    successfulRequest(mapResponseToDomain(bookResult));
                }
            }

            @Override
            public void onFailure(@NotNull Call<BooksApiResponse> call, @NotNull Throwable t) {
                Log.e("error", t.toString());
                errorRequest();
            }
        });
    }
}
