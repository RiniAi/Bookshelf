package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.room.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookChallengeAdapter extends RecyclerView.Adapter<BookChallengeAdapter.BookViewHolder> {
    private Context context;
    private List<Book> books;

    public BookChallengeAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_book_challenge, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = getItem(position);
        if (book == null) {
            return;
        }
        Picasso.get().load(book.getImageLinks()).into(holder.cover);
        holder.author.setText(book.getAuthors());
        holder.title.setText(book.getTitle());
        float rating = book.getAverageRating();
        holder.averRating.setRating(rating);
    }

    private Book getItem(int position) {
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(position);
        }
    }

    @Override
    public int getItemCount() {
        if (books == null) {
            return 0;
        } else {
            return books.size();
        }
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView author;
        TextView title;
        RatingBar averRating;

        BookViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.iv_book_challenge);
            author = (TextView) itemView.findViewById(R.id.tv_author_book_challenge);
            title = (TextView) itemView.findViewById(R.id.tv_title_book_challenge);
            averRating = (RatingBar) itemView.findViewById(R.id.tv_rating_book_challenge);
        }
    }
}
