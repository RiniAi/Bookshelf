package com.example.bookshelf.features.bookedit;

import android.os.Bundle;
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

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class EditBookActivity extends AppCompatActivity implements EditBookContract.View {
    private boolean isFavorite = false;
    private Toolbar toolbar;
    private Spinner status;
    private TextView title;
    private TextView author;
    private ImageView cover;
    private RatingBar averageRating;
    private RatingBar userRating;
    private DatePicker dateOfReading;
    private ToggleButton addToFavorite;
    private Button save;
    private Button delete;
    @Inject
    EditBookContract.Presenter presenter;
    @Inject
    SpinnerUtils spinnerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().injectEditBookActivity(this);
        ((BasePresenter) presenter).setView(this);
        setContentView(R.layout.activity_edit_book);
        initControls();
        presenter.onStartWitchData(getIntent().getExtras());
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title);
        author = (TextView) findViewById(R.id.tv_author);
        cover = (ImageView) findViewById(R.id.iv_cover);
        averageRating = (RatingBar) findViewById(R.id.rb_aver_rating);
        userRating = (RatingBar) findViewById(R.id.rb_user_rating);
        status = (Spinner) findViewById(R.id.spinner_status);
        dateOfReading = (DatePicker) findViewById(R.id.date_of_reading);
        addToFavorite = (ToggleButton) findViewById(R.id.btn_favorite);
        save = (Button) findViewById(R.id.btn_save);
        delete = (Button) findViewById(R.id.btn_delete);

        addToFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                isFavorite = isChecked;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.insertOrUpdateBook(userRating.getRating(),
                        status.getSelectedItem().toString(), isFavorite);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteBook();
                finish();
            }
        });

        buildToolbar();
        buildStatusSpinner();
    }

    private void buildToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.edit_book_title);
    }

    private void buildStatusSpinner() {
        ArrayAdapter<?> statusAdapter = ArrayAdapter.createFromResource(this, R.array.edit_book_status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);
    }

    @Override
    public void showBook(Book book) {
        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        averageRating.setRating(book.getAverageRating());
        userRating.setRating(book.getUserRating());
        addToFavorite.setChecked(book.isFavorite);
        isFavorite = book.isFavorite;
    }

    @Override
    public void showCover(String image) {
        Picasso.get().load(image).into(cover);
    }

    @Override
    public void showBrokenCover() {
        this.cover.setImageResource(R.drawable.ic_broken_image);
    }

    @Override
    public void showStatus(Book.BookStatus bookStatus) {
        spinnerUtils.getSelection(status, bookStatus);
    }

    @Override
    public void updateDate(int year, int month, int dayOfMonth) {
        dateOfReading.updateDate(year, month, dayOfMonth);
    }

    @Override
    public void showButtonDelete() {
        delete.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButtonDelete() {
        delete.setVisibility(View.GONE);
    }

    @Override
    public void showDate() {
        int year = dateOfReading.getYear();
        int month = dateOfReading.getMonth();
        int dayOfMonth = dateOfReading.getDayOfMonth();
        presenter.setDate(year, month, dayOfMonth);
    }
}
