package com.example.bookshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Models.Book;

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
        holder.authorBook.setText(book.getAuthors());
        holder.titleBook.setText(book.getTitle());
        String rating = String.valueOf(book.getAverageRating());
        holder.ratingBook.setText(rating);
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
        TextView authorBook;
        TextView titleBook;
        TextView ratingBook;

        BookViewHolder(View itemView) {
            super(itemView);

            authorBook = (TextView) itemView.findViewById(R.id.tv_author_book_challenge);
            titleBook = (TextView) itemView.findViewById(R.id.tv_title_book_challenge);
            ratingBook = (TextView) itemView.findViewById(R.id.tv_rating_book_challenge);
        }
    }
}
