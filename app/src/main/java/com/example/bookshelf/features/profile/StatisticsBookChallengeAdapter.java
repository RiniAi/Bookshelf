package com.example.bookshelf.features.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bookshelf.R;
import com.example.bookshelf.base.BaseStatisticBookChallengeAdapter;
import com.example.bookshelf.base.BaseViewHolder;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import javax.inject.Inject;

public class StatisticsBookChallengeAdapter extends BaseStatisticBookChallengeAdapter<BookChallenge, StatisticsBookChallengeAdapter.BookChallengeViewHolder> {
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;

    @Inject
    public StatisticsBookChallengeAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BookChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_statistics_book_challenge, parent, false);
        return new StatisticsBookChallengeAdapter.BookChallengeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookChallengeViewHolder holder, int position) {
        BookChallenge bookChallenge = getItem(position);
        if (bookChallenge == null) {
            return;
        }
        holder.year.setText(String.valueOf(bookChallenge.getYear()));
        holder.progress.setText(String.valueOf(searchBookWithStatusUseCase.run(Book.BookStatus.FINISH_READING).size()));
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
