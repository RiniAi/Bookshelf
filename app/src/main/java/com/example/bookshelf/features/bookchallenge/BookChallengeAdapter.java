package com.example.bookshelf.features.bookchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.base.BaseAdapter;
import com.example.bookshelf.base.BaseViewHolder;
import com.example.bookshelf.database.Book;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class BookChallengeAdapter extends BaseAdapter<Book, BookChallengeAdapter.BookViewHolder> {
    private OnItemClickListener onClickListener;

    public void setOnItemClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Inject
    public BookChallengeAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_book_challenge, parent, false);
        return new BookViewHolder(view, onClickListener);
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

        BookViewHolder(View itemView, OnItemClickListener onClickListener) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            cover.setClipToOutline(true);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            userRating = (TextView) itemView.findViewById(R.id.tv_rating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onClickListener != null && position != RecyclerView.NO_POSITION) {
                        onClickListener.onItemClick(getItem(position));
                    }
                }

            });
        }
    }
}
