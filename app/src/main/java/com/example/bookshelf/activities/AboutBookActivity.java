package com.example.bookshelf.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.squareup.picasso.Picasso;

public class AboutBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "book";
    private Book book;

    private TextView title;
    private TextView author;
    private ImageView image;
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

        getBook();
        initControls();
        fillFields();
    }

    private void getBook() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }
    }

    private void initControls() {
        title = (findViewById(R.id.tv_title_about_book));
        author = (findViewById(R.id.tv_author_about_book));
        image = (findViewById(R.id.img_about_book));
        rating = (findViewById(R.id.rb_rating_about_book));
        publishedDate = (findViewById(R.id.tv_published_date_about_book));
        publisher = (findViewById(R.id.tv_publisher_about_book));
        pageCount = (findViewById(R.id.tv_page_count_about_book));
        lang = (findViewById(R.id.tv_lang_about_book));
        description = (findViewById(R.id.tv_description_about_book));
    }

    private void fillFields() {
        title.setText(book.getTitle());
        author.setText(book.getAuthors());
        Picasso.get().load(book.getImageURL()).into(image);
        rating.setRating(book.getAverageRating());
        publishedDate.setText(book.getPublishedDate());
        publisher.setText(book.getPublisher());
        pageCount.setText(String.valueOf(book.getPageCount()));
        lang.setText(book.getLang());
        description.setText(book.getDescription());
    }
}

