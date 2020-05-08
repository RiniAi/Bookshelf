package com.example.bookshelf.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookssearch.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class RequestBooksUseCase {
    @Inject
    Repository repository;

    @Inject
    public RequestBooksUseCase() {
    }

    public Single<List<Book>> run(Params params) {
        return repository.getBooks(params.getQuery());
    }

    public static class Params {
        String query;
        public Params(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return query != null ? query.equals(params.query) : params.query == null;
        }

        @Override
        public int hashCode() {
            return query != null ? query.hashCode() : 0;
        }
    }
}