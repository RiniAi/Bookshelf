package com.example.bookshelf.features.bookabout;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.databinding.ActivityAboutBookBinding;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class AboutBookActivity extends AppCompatActivity implements AboutBookContract.View {
    private ActivityAboutBookBinding binding;
    @Inject
    AboutBookContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = ActivityAboutBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter.onStartWithData(getIntent().getExtras());
        setSupportActionBar(binding.toolbarAboutBook.toolbar);
    }

    @Override
    public void showBook(Book book) {
        binding.rbRating.setRating(book.getAverageRating());
        binding.tvPublishedDate.setText(book.getPublishedDate());
        binding.tvPublisher.setText(book.getPublisher());
        binding.tvPageCount.setText(String.valueOf(book.getPageCount()));
        binding.tvLang.setText(book.getLanguage());
        binding.tvDescription.setText(book.getDescription());
        binding.ivCover.setClipToOutline(true);
        binding.toolbarAboutBook.toolbar.setTitle(getString(R.string.about_book_title, book.getAuthors(), book.getTitle()));
    }

    @Override
    public void showBookCover(String cover) {
        Picasso.get().load(cover).into(binding.ivCover);
    }

    @Override
    public void showBookBrokenCover() {
        binding.ivCover.setImageResource(R.drawable.ic_broken_image);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(AboutBookActivity.this, R.string.book_is_not_found, Toast.LENGTH_SHORT).show();
    }
}
