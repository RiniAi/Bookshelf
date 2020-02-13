package com.example.bookshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookshelf.Models.Book;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private ArrayList<Book> bookArrayList;

    public BookAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_book_list, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookArrayList.get(position);

        holder.imgBook.setImageResource(book.getImage());
        holder.authorBook.setText(book.getAuthors());
        holder.titleBook.setText(book.getTitle());

        String rating = String.valueOf(book.getAverageRating());
        holder.ratingBook.setText(rating);
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();

    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView authorBook;
        TextView titleBook;
        TextView ratingBook;
        Button button;

        BookViewHolder(View itemView) {
            super(itemView);

            imgBook = (ImageView) itemView.findViewById(R.id.img_book_list);
            authorBook = (TextView) itemView.findViewById(R.id.tv_author_book_list);
            titleBook = (TextView) itemView.findViewById(R.id.tv_title_book_list);
            ratingBook = (TextView) itemView.findViewById(R.id.tv_rating_book_list);
            button = (Button) itemView.findViewById(R.id.btn_edit_book_list);
        }
    }
}
