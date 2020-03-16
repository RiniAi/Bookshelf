package com.example.bookshelf.activities;

import android.content.Intent;

import com.example.bookshelf.room.Book;

public class MainPresenter {
    private Intent intent;
    private MainActivity mainActivity;

    public MainPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onItemClick(Book book) {
        intent = new Intent(mainActivity, AboutBookActivity.class);
        intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        mainActivity.startActivity(intent);
    }

    public void onEditClick(Book book) {
        intent = new Intent(mainActivity, EditBookActivity.class);
        intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
        mainActivity.startActivity(intent);
    }
}
