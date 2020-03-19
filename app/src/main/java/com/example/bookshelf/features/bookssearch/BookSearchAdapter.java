package com.example.bookshelf.features.bookssearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.base.BaseAdapter;
import com.example.bookshelf.base.BaseViewHolder;
import com.example.bookshelf.database.Book;
import com.squareup.picasso.Picasso;

public class BookSearchAdapter extends BaseAdapter<Book, BookSearchAdapter.BookViewHolder> {
    private OnItemClickListener onClickListener;
    private OnEditClickListener onEditListener;

    public BookSearchAdapter(Context context) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_search, parent, false);
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
        holder.author.setText(book.getAuthors());
        holder.title.setText(book.getTitle());
    }

    class BookViewHolder extends BaseViewHolder {
        ImageView cover;
        TextView author;
        TextView title;
        ImageButton button;

        BookViewHolder(View itemView, OnItemClickListener onClickListener, OnEditClickListener onEditListener) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.iv_search_activity);
            cover.setClipToOutline(true);
            author = (TextView) itemView.findViewById(R.id.tv_author_search_activity);
            title = (TextView) itemView.findViewById(R.id.tv_title_search_activity);
            button = (ImageButton) itemView.findViewById(R.id.btn_search_activity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onClickListener != null && position != RecyclerView.NO_POSITION) {
                        onClickListener.onItemClick(getItem(position));
                    }
                }

            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onEditListener != null && position != RecyclerView.NO_POSITION) {
                        onEditListener.onEditClick(getItem(position));
                    }
                }
            });
        }
    }
}
