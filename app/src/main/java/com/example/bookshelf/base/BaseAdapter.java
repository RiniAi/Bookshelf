package com.example.bookshelf.base;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.database.book.Book;

import java.util.List;

import static com.example.bookshelf.features.bookssearch.BookSearchAdapter.VIEW_TYPE_ITEM;
import static com.example.bookshelf.features.bookssearch.BookSearchAdapter.VIEW_TYPE_LOADING;

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

    public List<T> getList() {
        return list;
    }

    public T getItem(int position) {
        return list.isEmpty() ? null : list.get(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public Context getContext() {
        return context;
    }

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public interface OnEditClickListener {
        void onEditClick(Book book);
    }
}
