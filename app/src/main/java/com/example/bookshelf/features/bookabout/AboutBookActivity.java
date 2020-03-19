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
    private AboutBookPresenter presenter;
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
        presenter.onStart();
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (findViewById(R.id.tv_title_about_book));
        author = (findViewById(R.id.tv_author_about_book));
        cover = (findViewById(R.id.iv_about_book));
        coverBack = (findViewById(R.id.iv_back_about_book));
        rating = (findViewById(R.id.rb_rating_about_book));
        publishedDate = (findViewById(R.id.tv_published_date_about_book));
        publisher = (findViewById(R.id.tv_publisher_about_book));
        pageCount = (findViewById(R.id.tv_page_count_about_book));
        lang = (findViewById(R.id.tv_lang_about_book));
        description = (findViewById(R.id.tv_description_about_book));

        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        if (book.getImageLinks() == null) {
            cover.setImageResource(R.drawable.ic_broken_image);
            coverBack.setImageResource(R.drawable.ic_broken_image);
        } else {
            Picasso.get().load(book.getImageLinks()).into(cover);
            Picasso.get().load(book.getImageLinks()).into(coverBack);
        }
        cover.setClipToOutline(true);
        rating.setRating(book.getAverageRating());
        publishedDate.setText(book.getPublishedDate());
        publisher.setText(book.getPublisher());
        pageCount.setText(String.valueOf(book.getPageCount()));
        lang.setText(book.getLanguage());
        description.setText(book.getDescription());

        buildToolbar();
    }

    private void buildToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about_book_title);
    }

}
