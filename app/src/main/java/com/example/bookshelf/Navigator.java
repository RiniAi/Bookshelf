package com.example.bookshelf;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.features.MainActivity;
import com.example.bookshelf.features.authentication.LoginRegisterActivity;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookedit.EditBookPresenter;

import javax.inject.Inject;

public class Navigator {
    @Inject
    Context context;

    @Inject
    public Navigator() {
    }

    public void editBook(Book book) {
        Intent intent = new Intent(context, EditBookActivity.class);
        intent.putExtra(EditBookPresenter.EXTRA_BOOK, book);
        context.startActivity(intent);
    }

    public void openMain() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void openAuthentication() {
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public Intent openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        return intent;
    }
}
