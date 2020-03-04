package com.example.bookshelf;

import com.example.bookshelf.models.BooksApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApiService {
    int QUERY_COUNTER = 40;
    /**
     * Requests books list from the Google API using keyword
     * @param query user request
     * @param count maximum number of query results returned, does not exceed 40!
     * @return books list from response
     */
    @GET("volumes/")
    Call<BooksApiResponse> getBooks(@Query("q") String query, @Query("maxResults") int count);
}
