package com.example.bookshelf.features.bookabout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.databinding.FragmentAboutBookBinding;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import static com.example.bookshelf.features.bookabout.AboutBookPresenter.EXTRA_BOOK;

public class AboutBookFragment extends Fragment implements AboutBookContract.View {
    private FragmentAboutBookBinding binding;
    @Inject
    AboutBookContract.Presenter presenter;

    public static AboutBookFragment newInstance(Book book) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_BOOK, book);
        AboutBookFragment fragment = new AboutBookFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentAboutBookBinding.inflate(inflater, container, false);
        presenter.onStartWithData(getArguments());
        binding.toolbarAboutBook.toolbar.setNavigationOnClickListener(view -> getActivity().onBackPressed());
        return binding.getRoot();
    }

    @Override
    public void showBook(Book book) {
        binding.rbRating.setRating(book.getAverageRating());
        binding.tvPublishedDate.setText(book.getPublishedDate());
        binding.tvPublisher.setText(book.getPublisher());
        binding.tvPageCount.setText(String.valueOf(book.getPageCount()));
        binding.tvLang.setText(book.getLanguage());
        binding.tvDescription.setText(book.getDescription());
        binding.ivCover.setClipToOutline(true);
        binding.toolbarAboutBook.toolbar.setTitle(getString(R.string.about_book_title, book.getAuthors(), book.getTitle()));
    }

    @Override
    public void showBookCover(String cover) {
        Picasso.get().load(cover).into(binding.ivCover);
    }

    @Override
    public void showBookBrokenCover() {
        binding.ivCover.setImageResource(R.drawable.ic_broken_image);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getActivity(), R.string.book_is_not_found, Toast.LENGTH_SHORT).show();
    }
}
