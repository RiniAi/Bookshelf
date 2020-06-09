package com.example.bookshelf.features.bookedit;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.databinding.ActivityEditBookBinding;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class EditBookActivity extends AppCompatActivity implements EditBookContract.View {
    private boolean isFavorite = false;
    private ActivityEditBookBinding binding;
    @Inject
    EditBookContract.Presenter presenter;
    @Inject
    SpinnerUtils spinnerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = ActivityEditBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        buildButtons();
        presenter.onStartWithData(getIntent().getExtras());
        setSupportActionBar(binding.toolbarEditBook.toolbar);
        binding.toolbarEditBook.toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void buildButtons() {
        binding.btnFavorite.setOnCheckedChangeListener((compoundButton, isChecked) -> isFavorite = isChecked);

        binding.btnSave.setOnClickListener(view -> {
            presenter.insertOrUpdateBook(binding.rbUserRating.getRating(),
                    binding.spinnerStatus.getSelectedItem().toString(), isFavorite);
            finish();
        });

        binding.btnDelete.setOnClickListener(view -> {
            presenter.deleteBook();
            finish();
        });

        buildStatusSpinner();
    }

    private void buildStatusSpinner() {
        ArrayAdapter<?> statusAdapter = new ArrayAdapter<Book.BookStatus>(this, R.layout.spinner_item, Book.BookStatus.values());
        statusAdapter.setDropDownViewResource(R.layout.spinner_item);
        binding.spinnerStatus.setAdapter(statusAdapter);
    }

    @Override
    public void showBook(Book book) {
        binding.rbUserRating.setRating(book.getUserRating());
        binding.btnFavorite.setChecked(book.isFavorite());
        isFavorite = book.isFavorite();
        binding.toolbarEditBook.toolbar.setTitle(getString(R.string.edit_book_title, book.getAuthors(), book.getTitle()));
    }

    @Override
    public void showCover(String image) {
        Picasso.get().load(image).into(binding.ivCover);
    }

    @Override
    public void showBrokenCover() {
        binding.ivCover.setImageResource(R.drawable.ic_broken_image);
    }

    @Override
    public void showStatus(Book.BookStatus bookStatus) {
        spinnerUtils.getSelection(binding.spinnerStatus, bookStatus);
    }

    @Override
    public void updateDate(int year, int month, int dayOfMonth) {
        binding.dateOfReading.updateDate(year, month, dayOfMonth);
    }

    @Override
    public void showButtonDelete() {
        binding.btnDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButtonDelete() {
        binding.btnDelete.setVisibility(View.GONE);
    }

    @Override
    public void showDate() {
        int year = binding.dateOfReading.getYear();
        int month = binding.dateOfReading.getMonth();
        int dayOfMonth = binding.dateOfReading.getDayOfMonth();
        presenter.setDate(year, month, dayOfMonth);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(EditBookActivity.this, R.string.book_is_not_found, Toast.LENGTH_SHORT).show();
    }
}
