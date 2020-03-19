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

import com.example.bookshelf.R;
import com.example.bookshelf.database.Book;
import com.squareup.picasso.Picasso;

public class EditBookActivity extends AppCompatActivity implements EditBookContract.View {
    private boolean isFavorite = false;
    private EditBookContract.Presenter presenter;
    private Toolbar toolbar;
    private Spinner status;
    private TextView title;
    private TextView author;
    private ImageView cover;
    private RatingBar averRating;
    private RatingBar userRating;
    private DatePicker datePicker;
    private ToggleButton addFavorite;
    private Button save;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        initControls();
        presenter = new EditBookPresenter(this, this, getIntent().getExtras());
        presenter.onStart();
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title_edit_book);
        author = (TextView) findViewById(R.id.tv_author_edit_book);
        cover = (ImageView) findViewById(R.id.iv_edit_book);
        averRating = (RatingBar) findViewById(R.id.rb_aver_rating_edit_book);
        userRating = (RatingBar) findViewById(R.id.rb_user_rating_edit_book);
        status = (Spinner) findViewById(R.id.spinner_status_edit_book);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        addFavorite = (ToggleButton) findViewById(R.id.btn_favorite);
        save = (Button) findViewById(R.id.btn_save_edit_book);
        delete = (Button) findViewById(R.id.btn_delete_edit_book);

        addFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.edit_book_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setAuthors(String authors) {
        this.author.setText(authors);
    }

    @Override
    public void setImage(String image) {
        Picasso.get().load(image).into(cover);
    }

    @Override
    public void setBrokenImage() {
        this.cover.setImageResource(R.drawable.ic_broken_image);
    }

    @Override
    public void setAverRating(float averRating) {
        this.averRating.setRating(averRating);
    }

    @Override
    public void setUserRating(float userRating) {
        this.userRating.setRating(userRating);
    }

    @Override
    public void setStatus(Book.BookStatus s) {
        switch (s) {
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

    @Override
    public void updateDate(int year, int month, int dayOfMonth) {
        datePicker.updateDate(year, month, dayOfMonth);
    }

    @Override
    public void setFavorite(boolean isFavorite) {
        addFavorite.setChecked(isFavorite);
        this.isFavorite = isFavorite;
    }

    @Override
    public void showDelete() {
        delete.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDelete() {
        delete.setVisibility(View.GONE);
    }

    @Override
    public void getDate() {
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int dayOfMonth = datePicker.getDayOfMonth();
        presenter.setDate(year, month, dayOfMonth);
    }
}
