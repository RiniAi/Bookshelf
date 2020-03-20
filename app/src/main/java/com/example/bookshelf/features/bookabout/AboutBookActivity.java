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
        presenter = new AboutBookPresenter(this);
        presenter.onStartWitchData(getIntent().getExtras());
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (findViewById(R.id.tv_title));
        author = (findViewById(R.id.tv_author));
        coverBack = (findViewById(R.id.iv_cover_back));
        rating = (findViewById(R.id.rb_rating));
        publishedDate = (findViewById(R.id.tv_published_date));
        publisher = (findViewById(R.id.tv_publisher));
        pageCount = (findViewById(R.id.tv_page_count));
        lang = (findViewById(R.id.tv_lang));
        description = (findViewById(R.id.tv_description));
        cover = (findViewById(R.id.iv_cover));
        cover.setClipToOutline(true);
        buildToolbar();
    }

    private void buildToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about_book_title);
    }

    @Override
    public void setBookView(String title,
                            String authors,
                            float rating,
                            String publishedDate,
                            String publisher,
                            String pageCount,
                            String lang,
                            String description) {
        this.title.setText(title);
        this.author.setText(authors);
        this.rating.setRating(rating);
        this.publishedDate.setText(publishedDate);
        this.publisher.setText(publisher);
        this.pageCount.setText(pageCount);
        this.lang.setText(lang);
        this.description.setText(description);
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
}
