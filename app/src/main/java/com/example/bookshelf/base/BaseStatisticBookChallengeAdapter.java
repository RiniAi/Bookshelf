package com.example.bookshelf.base;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseStatisticBookChallengeAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    private Context context;
    private List<T> list;

    public BaseStatisticBookChallengeAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public T getItem(int position) {
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
}
