package com.example.bookshelf.features.bookedit;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;

import com.example.bookshelf.App;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.BookStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class EditBookPresenter extends BasePresenter<EditBookContract.View> implements EditBookContract.Presenter {
    public static final String EXTRA_BOOK = "book";
    private Book book;
    private String date;
    @Inject
    Context context;
    @Inject
    BookStorage storage;

    @Override
    public void onStartWitchData(Bundle bundle) {
        App.getAppComponent().presenterComponent().inject(this);
        loadBook(bundle);
        searchBook();
    }

    private void loadBook(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);

            view.showBook(book);
            loadCover();
            initStatus();
            loadStatus();
        }
    }

    private void loadCover() {
        String image = book.getImageLinks();
        if (image == null) {
            view.showBrokenCover();
        } else {
            view.showCover(image);
        }
    }

    private void initStatus() {
        Book.BookStatus.resolveStatuses(context,Book.BookStatus.values());
    }

    private void loadStatus() {
        if (book.getStatus() != null) {
            view.showStatus(book.getStatus());
        }
        if (book.isFinishedOrQuit()) {
            getDate();
        }
    }

    private void getDate() {
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
            view.showButtonDelete();
        } else {
            view.hideButtonDelete();
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
        view.showDate();
        book.userRating = rating;
        book.isFavorite = isFavorite;
        book.setStatus(BookStatusConverter.fromStringToStatus(status));
        if (book.isFinishedOrQuit()) {
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
