package com.example.bookshelf.di;

import com.example.bookshelf.network.GoogleBooksApiService;
import com.example.bookshelf.network.RetrofitClientInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {
    @Provides
    public GoogleBooksApiService providesService() {
        return RetrofitClientInstance.getRetrofitInstance().create(GoogleBooksApiService.class);
    }
}
