package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.room.Book;
import com.squareup.picasso.Picasso;

public class BookChallengeAdapter extends BaseAdapter<Book, BookChallengeAdapter.BookViewHolder> {
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public BookChallengeAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_book_challenge, parent, false);
        return new BookViewHolder(view, listener);
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
        holder.userRating.setText(String.valueOf(book.getUserRating()));
    }

    class BookViewHolder extends BaseViewHolder {
        ImageView cover;
        TextView author;
        TextView title;
        TextView userRating;

        BookViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.iv_book_challenge);
            cover.setClipToOutline(true);
            author = (TextView) itemView.findViewById(R.id.tv_author_book_challenge);
            title = (TextView) itemView.findViewById(R.id.tv_title_book_challenge);
            userRating = (TextView) itemView.findViewById(R.id.tv_rating_book_challenge);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }

            });
        }
    }
}
