package com.example.bookshelf.features.bookedit;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;

import com.example.bookshelf.App;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.usecases.DeleteBookUseCase;
import com.example.bookshelf.features.usecases.InsertOrUpdateBookUseCase;
import com.example.bookshelf.features.usecases.SearchBookUseCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import static com.example.bookshelf.database.Book.BookStatus.resolveStatuses;

public class EditBookPresenter extends BasePresenter<EditBookContract.View> implements EditBookContract.Presenter {
    public static final String EXTRA_BOOK = "book";
    private Book book;
    private String date;
    @Inject
    Context context;
    @Inject
    SearchBookUseCase searchUseCase;
    @Inject
    InsertOrUpdateBookUseCase insertOrUpdateUseCase;
    @Inject
    DeleteBookUseCase deleteUseCase;

    public EditBookPresenter() {
        App.getAppComponent().presenterComponent().inject(this);
    }

    @Override
    public void onStartWithData(Bundle bundle) {
        loadBook(bundle);
        searchBook();
    }

    private void loadBook(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
            view.showBook(book);
            loadCover();
            resolveStatuses(context, Book.BookStatus.values());
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
        SearchBookUseCase.Params params = new SearchBookUseCase.Params(book);
        Book bookDb = searchUseCase.run(params);
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
        InsertOrUpdateBookUseCase.Params params = new InsertOrUpdateBookUseCase.Params(book, rating, status, isFavorite, date);
        insertOrUpdateUseCase.run(params);
    }

    @Override
    public void deleteBook() {
        DeleteBookUseCase.Params params = new DeleteBookUseCase.Params(book);
        deleteUseCase.run(params);
    }
}

