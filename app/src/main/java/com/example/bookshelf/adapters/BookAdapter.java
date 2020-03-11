package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.room.Book;
import com.example.bookshelf.room.BookStatusConverter;
import com.squareup.picasso.Picasso;

public class BookAdapter extends BaseAdapter<Book, BookAdapter.BookViewHolder> {
    private OnItemClickListener listener;

    public BookAdapter(Context context) {
        super(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_book, parent, false);
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
        float rating = book.getAverageRating();
        holder.rating.setRating(rating);
        holder.userRating.setRating(book.getUserRating());
        if (book.getReadDate().equals("")) {
            holder.data.setVisibility(View.GONE);
        } else {
            holder.data.setVisibility(View.VISIBLE);
            holder.readData.setText(book.getReadDate());
        }
        if (!book.isFavorite()) {
            holder.favorite.setImageResource(R.drawable.ic_favorite_off);
        } else {
            holder.favorite.setImageResource(R.drawable.ic_favorite_on);
        }
        holder.status.setText(BookStatusConverter.fromStatusToString(book.getStatus()));
    }

    class BookViewHolder extends BaseViewHolder {
        ImageView cover;
        TextView author;
        TextView title;
        RatingBar rating;
        RatingBar userRating;
        LinearLayout data;
        TextView readData;
        TextView status;
        ImageView favorite;
        ImageButton button;

        BookViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.iv_book_list);
            author = (TextView) itemView.findViewById(R.id.tv_author_book_list);
            title = (TextView) itemView.findViewById(R.id.tv_title_book_list);
            rating = (RatingBar) itemView.findViewById(R.id.tv_rating_book_list);
            userRating = (RatingBar) itemView.findViewById(R.id.tv_user_rating_book_list);
            data = (LinearLayout) itemView.findViewById(R.id.ll_readData_book_list);
            readData = (TextView) itemView.findViewById(R.id.tv_read_data_book_list);
            status = (TextView) itemView.findViewById(R.id.tv_status_book_list);
            favorite = (ImageView) itemView.findViewById(R.id.iv_favorite_book_list);
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
