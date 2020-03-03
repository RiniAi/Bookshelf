package com.example.bookshelf;

import com.example.bookshelf.models.BookItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApiService {
    /**
     * The method collects a request from the Google API service into a single heap
     * @param query - user request
     * @param count - maximum number of query results returned, does not exceed 40!
     * @return search query
     */
    @GET("volumes/")
    Call<BookItem> getBooks(@Query("q") String query, @Query("maxResults") int count);
}
