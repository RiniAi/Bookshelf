package com.example.bookshelf.features.bookedit;

import android.widget.Spinner;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;

public class SpinnerUtils {

    public void getSelection(Spinner spinner, Book.BookStatus bookStatus) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(BookStatusConverter.fromStatusToString(bookStatus))) {
                spinner.setSelection(i);
            }
        }
    }
}
