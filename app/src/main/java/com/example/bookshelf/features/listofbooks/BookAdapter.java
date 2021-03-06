package com.example.bookshelf.features.listofbooks;

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
import com.example.bookshelf.base.BaseAdapter;
import com.example.bookshelf.base.BaseViewHolder;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.book.BookStatusConverter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class BookAdapter extends BaseAdapter<Book, BookAdapter.BookViewHolder> {
    private OnItemClickListener onClickListener;
    private OnEditClickListener onEditListener;

    @Inject
    public BookAdapter(Context context) {
        super(context);
    }

    public void setOnItemClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnEditClickListener(OnEditClickListener onEditListener) {
        this.onEditListener = onEditListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_book, parent, false);
        return new BookViewHolder(view, onClickListener, onEditListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = getItem(position);
        if (book == null) {
            return;
        }
        if (book.getImageLinks() == null) {
            holder.cover.setImageResource(R.drawable.ic_broken_image);
        } else {
            Picasso.get().load(book.getImageLinks()).into(holder.cover);
        }
        if (book.getAuthors().equals("")) {
            holder.author.setText(R.string.book_search_author_unknown);

        } else {
            holder.author.setText(book.getAuthors());

        }
        if (book.getAuthors().equals("")) {
            holder.title.setText(R.string.book_search_title_unknown);

        } else {
            holder.title.setText(book.getTitle());
        }
        holder.userRating.setRating(book.getUserRating());
        if (book.getReadDate().equals("")) {
            holder.data.setVisibility(View.GONE);
        } else {
            holder.data.setVisibility(View.VISIBLE);
            holder.readData.setText(book.getReadDate());
        }

        holder.status.setText(BookStatusConverter.fromStatusToString(book.getStatus()));
    }

    class BookViewHolder extends BaseViewHolder {
        ImageView cover;
        TextView author;
        TextView title;
        RatingBar userRating;
        LinearLayout data;
        TextView readData;
        TextView status;
        ImageButton button;

        BookViewHolder(View itemView, OnItemClickListener onClickListener, OnEditClickListener onEditListener) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            cover.setClipToOutline(true);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            userRating = (RatingBar) itemView.findViewById(R.id.tv_user_rating);
            data = (LinearLayout) itemView.findViewById(R.id.ll_read_data);
            readData = (TextView) itemView.findViewById(R.id.tv_read_data);
            status = (TextView) itemView.findViewById(R.id.tv_status);
            button = (ImageButton) itemView.findViewById(R.id.btn_edit);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (onClickListener != null && position != RecyclerView.NO_POSITION) {
                    onClickListener.onItemClick(getItem(position));
                }
            });
            button.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (onEditListener != null && position != RecyclerView.NO_POSITION) {
                    onEditListener.onEditClick(getItem(position));
                }
            });
        }
    }
}
