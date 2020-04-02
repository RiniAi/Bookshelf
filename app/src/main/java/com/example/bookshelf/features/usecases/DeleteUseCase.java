package com.example.bookshelf.features.usecases;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.LocalBookStorage;

import javax.inject.Inject;

public class DeleteUseCase {
    @Inject
    LocalBookStorage storage;

    @Inject
    public DeleteUseCase() {
    }

    public static class Params<T> {
        private T book;

        public T getBook() {
            return book;
        }

        public Params(T book) {
            this.book = book;
        }
    }

    public void run(Params params) {
        storage.delete((Book) params.getBook());
    }
}
