package com.example.bookshelf.features.bookedit;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;

import com.example.bookshelf.R;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.BookStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;
import static com.example.bookshelf.database.Book.BookStatus.IN_THE_PROCESS_OF_READING;
import static com.example.bookshelf.database.Book.BookStatus.PLAN_READING;
import static com.example.bookshelf.database.Book.BookStatus.QUIT_READING;

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
        loadBook(bundle);
        searchBook();
    }

    private void loadBook(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);

            view.showBook(book);
            buildStatusSpinner();
            loadCover();
            loadStatus();
        }
    }

    private void buildStatusSpinner() {
        List<StatusBook> list = new ArrayList<StatusBook>();
        list.add(new StatusBook(context.getString(R.string.edit_book_process_status), IN_THE_PROCESS_OF_READING));
        list.add(new StatusBook(context.getString(R.string.edit_book_plan_status), PLAN_READING));
        list.add(new StatusBook(context.getString(R.string.edit_book_finish_status), FINISH_READING));
        list.add(new StatusBook(context.getString(R.string.edit_book_quit_status), QUIT_READING));

        view.buildStatusSpinner(list);
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
        if (book.getStatus() == FINISH_READING || book.getStatus() == QUIT_READING) {
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
        if (book.getStatus() == FINISH_READING || book.getStatus() == QUIT_READING) {
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
