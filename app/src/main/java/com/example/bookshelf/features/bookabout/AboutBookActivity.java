package com.example.bookshelf.features.bookabout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bookshelf.R;
import com.squareup.picasso.Picasso;

public class AboutBookActivity extends AppCompatActivity implements AboutBookContract.View {
    private AboutBookContract.Presenter presenter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_book);

        initControls();
        getBundle();
    }

    private void getBundle() {
        presenter = new AboutBookPresenter(this);
        presenter.getBook(getIntent().getExtras());
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (findViewById(R.id.tv_title_about_book));
        author = (findViewById(R.id.tv_author_about_book));
        coverBack = (findViewById(R.id.iv_back_about_book));
        rating = (findViewById(R.id.rb_rating_about_book));
        publishedDate = (findViewById(R.id.tv_published_date_about_book));
        publisher = (findViewById(R.id.tv_publisher_about_book));
        pageCount = (findViewById(R.id.tv_page_count_about_book));
        lang = (findViewById(R.id.tv_lang_about_book));
        description = (findViewById(R.id.tv_description_about_book));
        cover = (findViewById(R.id.iv_about_book));
        cover.setClipToOutline(true);
        buildToolbar();
    }

    private void buildToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about_book_title);
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
        Picasso.get().load(image).into(coverBack);
    }

    @Override
    public void setBrokenImage() {
        this.cover.setImageResource(R.drawable.ic_broken_image);
        this.coverBack.setImageResource(R.drawable.ic_broken_image);
    }

    @Override
    public void setRating(float rating) {
        this.rating.setRating(rating);
    }

    @Override
    public void setPublishedDate(String publishedDate) {
        this.publishedDate.setText(publishedDate);
    }

    @Override
    public void setPublisher(String publisher) {
        this.publisher.setText(publisher);
    }

    @Override
    public void setPageCount(String pageCount) {
        this.pageCount.setText(pageCount);
    }

    @Override
    public void setLanguage(String lang) {
        this.lang.setText(lang);
    }

    @Override
    public void setDescription(String description) {
        this.description.setText(description);
    }
}
