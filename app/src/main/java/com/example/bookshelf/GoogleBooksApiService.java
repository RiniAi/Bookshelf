package com.example.bookshelf;

import com.example.bookshelf.models.BookItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApiService {
    @GET("volumes/")
    Call<BookItem> getBooks(@Query("q") String query);
}
