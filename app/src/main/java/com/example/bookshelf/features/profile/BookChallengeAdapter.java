package com.example.bookshelf.features.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bookshelf.R;
import com.example.bookshelf.base.BaseBookChallengeAdapter;
import com.example.bookshelf.base.BaseViewHolder;
import com.example.bookshelf.database.BookChallenge;

import javax.inject.Inject;

public class BookChallengeAdapter extends BaseBookChallengeAdapter <BookChallenge, BookChallengeAdapter.BookChallengeViewHolder> {

    @Inject
    public BookChallengeAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BookChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_statistics_book_challenge, parent, false);
        return new BookChallengeAdapter.BookChallengeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookChallengeViewHolder holder, int position) {
        BookChallenge bookChallenge = getItem(position);
        if (bookChallenge == null) {
            return;
        }
        holder.year.setText(String.valueOf(bookChallenge.getYear()));
        holder.progress.setText(String.valueOf(bookChallenge.getProgress()));
        holder.counter.setText(String.valueOf(bookChallenge.getCounter()));
    }

    class BookChallengeViewHolder extends BaseViewHolder {
        TextView year;
        TextView progress;
        TextView counter;

        BookChallengeViewHolder(View itemView) {
            super(itemView);
            year = (TextView) itemView.findViewById(R.id.tv_year);
            progress = (TextView) itemView.findViewById(R.id.tv_progress_list_item);
            counter = (TextView) itemView.findViewById(R.id.tv_counter_list_item);
        }

    }
}
