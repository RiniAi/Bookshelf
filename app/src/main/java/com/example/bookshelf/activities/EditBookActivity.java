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

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;
import com.example.bookshelf.room.BookEntity;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "book";
    private boolean isFavorite = false;
    private Spinner spinner;
    private Book book;
    private String date;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }

        buildStatusSpinner();
        initControls();
    }

    private void buildStatusSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner_status_edit_book);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.status_edit_book, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initControls() {
        TextView title = (TextView) findViewById(R.id.tv_title_edit_book);
        TextView author = (TextView) findViewById(R.id.tv_author_edit_book);
        ImageView imageView = (ImageView) findViewById(R.id.iv_edit_book);
        RatingBar rating = (RatingBar) findViewById(R.id.tv_averRating_edit_book);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rb_rating_edit_book);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        ToggleButton favoriteClick = (ToggleButton) findViewById(R.id.btn_favorite);
        Button save = (Button) findViewById(R.id.btn_save_edit_book);
        Button delete = (Button) findViewById(R.id.btn_delete_edit_book);

        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        Picasso.get().load(book.getImageURL()).into(imageView);
        rating.setRating(book.getAverageRating());

        favoriteClick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                isFavorite = isChecked;
            }
        });

        BookDatabase db = App.getInstance().getDatabase();
        BookDao bookDao = db.bookDao();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
                if (book != null) {
                    BookEntity bookEntity = new BookEntity();
                    bookEntity.authors = book.getAuthors();
                    bookEntity.title = book.getTitle();
                    bookEntity.imageLinks = book.getImageURL();
                    bookEntity.averageRating = book.getAverageRating();
                    bookEntity.userRating = ratingBar.getRating();
                    bookEntity.favorite = isFavorite;
                    bookEntity.status = spinner.getSelectedItem().toString();
                    bookEntity.readDate = date;
                    bookDao.update(bookEntity);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookEntity bookEntity = new BookEntity();
                bookEntity.authors = book.getAuthors();
                bookEntity.title = book.getTitle();
                bookEntity.imageLinks = book.getImageURL();
                bookEntity.averageRating = book.getAverageRating();
                bookDao.update(bookEntity);
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
