package com.example.bookshelf.features.bookedit;

import android.widget.Spinner;

import com.example.bookshelf.database.Book;

public class SpinnerUtils {

    public void getSelection(Spinner spinner, Book.BookStatus bookStatus) {
        for (int i = 0; i < spinner.getCount(); i++) {
            StatusBook status = (StatusBook) spinner.getItemAtPosition(i);
            if (status.getId() == bookStatus) {
                spinner.setSelection(i);
            }
        }
    }
}
