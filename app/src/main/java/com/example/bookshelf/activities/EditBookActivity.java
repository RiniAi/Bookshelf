package com.example.bookshelf.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.Book.BookStatus;
import com.example.bookshelf.room.BookDao;
import com.example.bookshelf.room.BookDatabase;
import com.example.bookshelf.room.BookEntity;

public class EditBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "book";
    private boolean isFavorite = false;
    private Spinner spinner;
    private Book book;

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
        ArrayAdapter<BookStatus> adapter = new ArrayAdapter<BookStatus>(this, android.R.layout.simple_spinner_item, BookStatus.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initControls() {
        Button button = (Button) findViewById(R.id.btn_save_edit_book);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rb_rating_edit_book);
        ToggleButton favoriteClick = (ToggleButton) findViewById(R.id.btn_favorite);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        favoriteClick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                isFavorite = isChecked;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder date = new StringBuilder()
                        .append(datePicker.getDayOfMonth()).append(".")
                        .append(datePicker.getMonth() + 1).append(".")
                        .append(datePicker.getYear());

                BookDatabase db = App.getInstance().getDatabase();
                BookDao bookDao = db.bookDao();
                if (book != null) {
                    BookEntity bookEntity = new BookEntity();
                    bookEntity.authors = book.getAuthors();
                    bookEntity.title = book.getTitle();
                    bookEntity.imageLinks = book.getImageURL();
                    bookEntity.averageRating = book.getAverageRating();
                    bookEntity.userRating = ratingBar.getRating();
                    bookEntity.favorite = isFavorite;
                    bookEntity.status = spinner.getSelectedItem().toString();
                    bookEntity.readDate = date.toString();
                    bookDao.update(bookEntity);
                }
            }
        });
    }
}
