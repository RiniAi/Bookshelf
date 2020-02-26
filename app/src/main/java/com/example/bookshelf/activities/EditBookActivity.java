package com.example.bookshelf.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book.BookStatus;

public class EditBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "book";

    boolean favorite = false;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        Bundle extras = getIntent().getExtras();
        // проверка на ноль и на extra - есть ли ключ

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

        favoriteClick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                favorite = isChecked;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
