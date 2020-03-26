package com.example.bookshelf.features.bookabout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class AboutBookActivity extends AppCompatActivity implements AboutBookContract.View {
    private Toolbar toolbar;
    private TextView title;
    private TextView author;
    private ImageView cover;
    private ImageView coverBack;
    private RatingBar rating;
    private TextView publishedDate;
    private TextView publisher;
    private TextView pageCount;
    private TextView lang;
    private TextView description;
    @Inject
    AboutBookContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().injectAboutBookActivity(this);
        ((BasePresenter)presenter).setView(this);
        setContentView(R.layout.activity_about_book);
        initControls();
        presenter.onStartWitchData(getIntent().getExtras());
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title);
        author = (TextView) findViewById(R.id.tv_author);
        coverBack = (ImageView) findViewById(R.id.iv_cover_back);
        rating = (RatingBar) findViewById(R.id.rb_rating);
        publishedDate = (TextView) findViewById(R.id.tv_published_date);
        publisher = (TextView) findViewById(R.id.tv_publisher);
        pageCount = (TextView) findViewById(R.id.tv_page_count);
        lang = (TextView) findViewById(R.id.tv_lang);
        description = (TextView) findViewById(R.id.tv_description);
        cover = (ImageView) findViewById(R.id.iv_cover);
        cover.setClipToOutline(true);
        buildToolbar();
    }

    private void buildToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about_book_title);
    }

    @Override
    public void showBook(Book book) {
        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        rating.setRating(book.getAverageRating());
        publishedDate.setText(book.getPublishedDate());
        publisher.setText(book.getPublisher());
        pageCount.setText(String.valueOf(book.getPageCount()));
        lang.setText(book.getLanguage());
        description.setText(book.getDescription());
    }

    @Override
    public void showBookCover(String cover) {
        Picasso.get().load(cover).into(this.cover);
        Picasso.get().load(cover).into(this.coverBack);
    }

    @Override
    public void showBookBrokenCover() {
        cover.setImageResource(R.drawable.ic_broken_image);
        coverBack.setImageResource(R.drawable.ic_broken_image);
    }
}
