package com.example.bookshelf.network;

import com.example.bookshelf.models.BooksApiResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApiService {
    int QUERY_COUNTER = 40;

    /**
     * Requests books list from the Google API using keyword
     *
     * @param query user request
     * @param count maximum number of query results returned, does not exceed 40!
     * @return books list from response
     */
    @GET("volumes/")
    Single<BooksApiResponse> getBooks(@Query("q") String query, @Query("maxResults") int count, @Query("startIndex") int index);
}
