package com.example.bookshelf.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bookshelf.R;
import com.example.bookshelf.room.Book;
import com.squareup.picasso.Picasso;

public class AboutBookActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK = "book";
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_book);

        getBook();
        initControls();
    }

    private void getBook() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }
    }

    private void initControls() {
        TextView title = (findViewById(R.id.tv_title_about_book));
        TextView author = (findViewById(R.id.tv_author_about_book));
        ImageView cover = (findViewById(R.id.iv_about_book));
        ImageView coverBack = (findViewById(R.id.iv_back_about_book));
        RatingBar rating = (findViewById(R.id.rb_rating_about_book));
        TextView publishedDate = (findViewById(R.id.tv_published_date_about_book));
        TextView publisher = (findViewById(R.id.tv_publisher_about_book));
        TextView pageCount = (findViewById(R.id.tv_page_count_about_book));
        TextView lang = (findViewById(R.id.tv_lang_about_book));
        TextView description = (findViewById(R.id.tv_description_about_book));

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
    }
}
