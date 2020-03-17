package com.example.bookshelf.features.bookedit;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bookshelf.R;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStatusConverter;
import com.example.bookshelf.database.BookStorage;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "book";
    private BookStorage storage = new BookStorage();
    private boolean isFavorite = false;
    private Spinner status;
    private Book book;
    private String date;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        initToolbar();
        getBook();
        initControls();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.edit_book_title);
    }

    private void getBook() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }
    }

    private void buildStatusSpinner() {
        status = (Spinner) findViewById(R.id.spinner_status_edit_book);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.edit_book_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);
    }

    private void initControls() {
        buildStatusSpinner();
        TextView title = (TextView) findViewById(R.id.tv_title_edit_book);
        TextView author = (TextView) findViewById(R.id.tv_author_edit_book);
        ImageView imageView = (ImageView) findViewById(R.id.iv_edit_book);
        RatingBar rating = (RatingBar) findViewById(R.id.tv_averRating_edit_book);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rb_rating_edit_book);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        ToggleButton favoriteClick = (ToggleButton) findViewById(R.id.btn_favorite);
        Button save = (Button) findViewById(R.id.btn_save_edit_book);
        Button delete = (Button) findViewById(R.id.btn_delete_edit_book);

        Book bookDb = storage.searchBookDb(book);
        if (bookDb != null) {
            delete.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.GONE);
        }
        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        Picasso.get().load(book.getImageLinks()).into(imageView);
        rating.setRating(book.getAverageRating());
        ratingBar.setRating(book.getUserRating());

        if (book.getStatus() == Book.BookStatus.FINISH_READING || book.getStatus() == Book.BookStatus.QUIT_READING) {
            stringConvertingToDatePicker();
        }
        favoriteClick.setChecked(book.isFavorite());
        isFavorite = book.isFavorite;

        if (book.getStatus() != null) {
            switch (book.getStatus()) {
                case IN_THE_PROCESS_OF_READING:
                    status.setSelection(0);
                    break;
                case PLAN_READING:
                    status.setSelection(1);
                    break;
                case FINISH_READING:
                    status.setSelection(2);
                    break;
                case QUIT_READING:
                    status.setSelection(3);
                    break;
            }
        }

        favoriteClick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                isFavorite = isChecked;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
                book.userRating = ratingBar.getRating();
                book.isFavorite = isFavorite;
                book.setStatus(BookStatusConverter.fromStringToStatus(status.getSelectedItem().toString()));
                if (book.getStatus() == Book.BookStatus.FINISH_READING || book.getStatus() == Book.BookStatus.QUIT_READING) {
                    book.readDate = date;
                } else {
                    book.readDate = "";
                }
                storage.insertOrUpdate(book);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storage.delete(book);
                finish();
            }
        });
    }

    private void getDate() {
        int dayOfMonth = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        date = DateUtils.formatDateTime(this, calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR);
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
        datePicker.updateDate(year, month, dayOfMonth);
    }
}
