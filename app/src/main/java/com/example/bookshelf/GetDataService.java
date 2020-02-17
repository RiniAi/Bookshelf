package com.example.bookshelf;

import com.example.bookshelf.Models.BookItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("volumes?q=harry+potter")
    Call<BookItem> getData();
}



