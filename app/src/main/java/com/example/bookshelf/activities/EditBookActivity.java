package com.example.bookshelf.activities;

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
import com.example.bookshelf.Storage;
import com.example.bookshelf.room.Book;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class EditBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "book";
    private Storage storage = new Storage();
    private boolean isFavorite = false;
    private Spinner spinner;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit_book);
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
        spinner = (Spinner) findViewById(R.id.spinner_status_edit_book);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.edit_book_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
                book.status = spinner.getSelectedItem().toString();
                if (book.status.equals("Reading") || book.status.equals("Not reading")) {
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
                book.authors = book.getAuthors();
                book.title = book.getTitle();
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
}
