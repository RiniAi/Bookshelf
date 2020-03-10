package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class Adapter<T> extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<T> list;

    public Adapter(Context context) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
