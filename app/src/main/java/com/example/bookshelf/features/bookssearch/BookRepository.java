package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.models.BooksApiResponse;
import com.example.bookshelf.models.BooksApiResponseItem;
import com.example.bookshelf.network.GoogleBooksApiService;
import com.example.bookshelf.network.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookshelf.network.BookMapper.mapResponseToDomain;
import static com.example.bookshelf.network.GoogleBooksApiService.QUERY_COUNTER;

public class BookRepository implements Repository {
    private List<BooksApiResponseItem> bookResult;
    @Inject
    public BookRepository() {
    }

    public void requestBooksFromApi(String query, SearchCall.responseListener responseListener) {
        bookResult = new ArrayList<>();
        GoogleBooksApiService service = RetrofitClientInstance.getRetrofitInstance().create(GoogleBooksApiService.class);
        Call<BooksApiResponse> call = service.getBooks(query, QUERY_COUNTER);
        call.enqueue(new Callback<BooksApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<BooksApiResponse> call, @NotNull Response<BooksApiResponse> response) {
                if (response.body() != null) {
                    bookResult = response.body().getItems();
                }
                responseListener.onSuccess(mapResponseToDomain(bookResult));
            }

            @Override
            public void onFailure(@NotNull Call<BooksApiResponse> call, @NotNull Throwable t) {
                responseListener.onFailure(t);
            }
        });
    }
}
