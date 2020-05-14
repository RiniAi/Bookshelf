package com.example.bookshelf.base;

public class BasePresenter<T> {
    public T view;

    public void setView(T view) {
        this.view = view;
    }
}
