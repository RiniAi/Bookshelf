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

import com.example.bookshelf.Models.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private ArrayList<Item> books;

    String author = "";
    String authors = "";
    String rating = "Not rating";

    public BookAdapter(Context context, ArrayList<Item> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Item book = books.get(position);

        Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail()).into(holder.imgBook);
        holder.titleBook.setText(book.getVolumeInfo().getTitle());

        if (!(book.getVolumeInfo().getAuthors().size() == 0)) {
            author = book.getVolumeInfo().getAuthors().get(0);
            holder.authorBook.setText(authors);
        }

        if (book.getVolumeInfo().getAuthors().size() == 1) {
            authors = book.getVolumeInfo().getAuthors().get(0);
        } else if (book.getVolumeInfo().getAuthors().size() > 1) {
            authors = book.getVolumeInfo().getAuthors().get(0);
            for (int j = 1; j < book.getVolumeInfo().getAuthors().size(); j++) {
                authors = authors + ", " + book.getVolumeInfo().getAuthors().get(j);
            }
        }

        if (book.getVolumeInfo().getAverageRating() == null) {
            holder.ratingBook.setText(rating);
        } else {
            String rating = String.valueOf(book.getVolumeInfo().getAverageRating());
            holder.ratingBook.setText(rating);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
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
