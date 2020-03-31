package com.example.bookshelf.features.bookedit;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
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
    private ImageView cover;
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
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        setContentView(R.layout.activity_edit_book);
        setSupportActionBar(toolbar);
        initControls();
        presenter.onStartWithData(getIntent().getExtras());
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        cover = (ImageView) findViewById(R.id.iv_cover);
        userRating = (RatingBar) findViewById(R.id.rb_user_rating);
        status = (Spinner) findViewById(R.id.spinner_status);
        dateOfReading = (DatePicker) findViewById(R.id.date_of_reading);
        addToFavorite = (ToggleButton) findViewById(R.id.btn_favorite);
        save = (Button) findViewById(R.id.btn_save);
        delete = (Button) findViewById(R.id.btn_delete);

        addToFavorite.setOnCheckedChangeListener((compoundButton, isChecked) -> isFavorite = isChecked);

        save.setOnClickListener(view -> {
            presenter.insertOrUpdateBook(userRating.getRating(),
                    status.getSelectedItem().toString(), isFavorite);
            finish();
        });

        delete.setOnClickListener(view -> {
            presenter.deleteBook();
            finish();
        });

        buildStatusSpinner();
    }

    private void updateToolbar(String title) {
        toolbar.setTitle(title);
    }

    private void buildStatusSpinner() {
        ArrayAdapter<?> statusAdapter = new ArrayAdapter<Book.BookStatus>(this, R.layout.spinner_item, Book.BookStatus.values());
        statusAdapter.setDropDownViewResource(R.layout.spinner_item);
        status.setAdapter(statusAdapter);
    }

    @Override
    public void showBook(Book book) {
        userRating.setRating(book.getUserRating());
        addToFavorite.setChecked(book.isFavorite);
        isFavorite = book.isFavorite;
        String title = getString(R.string.edit_book_title, book.getAuthors(), book.getTitle());
        updateToolbar(title);
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
