package com.example.bookshelf.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.room.Book;

public abstract class Adapter extends RecyclerView.Adapter<Adapter.BookViewHolder> {
    private List<T> books;

    public void setList(List<T> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public abstract void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position);

    Book getItem(int position) {
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

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

    }
}
