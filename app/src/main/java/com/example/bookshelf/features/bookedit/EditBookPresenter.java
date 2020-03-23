package com.example.bookshelf.features.bookedit;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.BookStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditBookPresenter implements EditBookContract.Presenter {
    public static final String EXTRA_BOOK = "book";
    private BookStorage storage = new BookStorage();
    private EditBookContract.View view;
    private Context context;
    private Book book;
    private String date;

    public EditBookPresenter(EditBookContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void onStartWitchData(Bundle bundle) {
        getBook(bundle);
        searchBook();
    }

    private void getBook(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);

            getBookView();
            getCover();
            getStatus();
        }
    }

    private void getBookView() {
        view.setBookView(book.getTitle(),
                book.getAuthors(),
                book.getAverageRating(),
                book.getUserRating(),
                book.isFavorite());
    }

    private void getCover() {
        String image = book.getImageLinks();
        if (image == null) {
            view.setBrokenImage();
        } else {
            view.setImage(image);
        }
    }

    private void getStatus() {
        if (book.getStatus() != null) {
            view.setStatus(book.getStatus());
        }
        if (book.getStatus() == Book.BookStatus.FINISH_READING || book.getStatus() == Book.BookStatus.QUIT_READING) {
            stringConvertingToDatePicker();
        }
    }

    private void stringConvertingToDatePicker() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(book.getReadDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        view.updateDate(year, month, dayOfMonth);
    }

    private void searchBook() {
        Book bookDb = storage.searchBookDb(book);
        if (bookDb != null) {
            view.showDelete();
        } else {
            view.hideDelete();
        }
    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        date = DateUtils.formatDateTime(context, calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR);
    }

    @Override
    public void insertOrUpdateBook(float rating, String status, boolean isFavorite) {
        view.getDate();
        book.userRating = rating;
        book.isFavorite = isFavorite;
        book.setStatus(BookStatusConverter.fromStringToStatus(status));
        if (book.getStatus() == Book.BookStatus.FINISH_READING || book.getStatus() == Book.BookStatus.QUIT_READING) {
            book.readDate = date;
        } else {
            book.readDate = "";
        }
        storage.insertOrUpdate(book);
    }

    @Override
    public void deleteBook() {
        storage.delete(book);
    }
}
