package com.example.bookshelf.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.room.Book;

import java.util.List;

public abstract class BaseAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    private Context context;
    private List<T> list;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    T getItem(int position) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(position);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public Context getContext() {
        return context;
    }

    public interface OnItemClickListener {
        void onItemClick(Book book);

        void onEditClick(Book book);
    }
}
