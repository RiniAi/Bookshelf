package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context context;
    private List<Book> books;
    private OnItemClickListener listener;

    public BookAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(Book book);

        void onEditClick(Book book);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        return new BookViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = getItem(position);
        if (book == null) {
            return;
        }
        Picasso.get().load(book.getImageURL()).into(holder.imgBook);
        holder.authorBook.setText(book.getAuthors());
        holder.titleBook.setText(book.getTitle());
        float rating = book.getAverageRating();
        holder.ratingBook.setRating(rating);
    }

    public Book getItem(int position) {
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
        ImageView imgBook;
        TextView authorBook;
        TextView titleBook;
        RatingBar ratingBook;
        ImageButton button;

        BookViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            imgBook = (ImageView) itemView.findViewById(R.id.img_book_list);
            authorBook = (TextView) itemView.findViewById(R.id.tv_author_book_list);
            titleBook = (TextView) itemView.findViewById(R.id.tv_title_book_list);
            ratingBook = (RatingBar) itemView.findViewById(R.id.tv_rating_book_list);
            button = (ImageButton) itemView.findViewById(R.id.btn_edit_book_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }

            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(getItem(position));
                    }
                }
            });
        }
    }
}
