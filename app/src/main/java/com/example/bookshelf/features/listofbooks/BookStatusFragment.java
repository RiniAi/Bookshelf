package com.example.bookshelf.features.listofbooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.book.BookStatusConverter;
import com.example.bookshelf.databinding.FragmentListOfBooksBinding;

import java.util.List;

import javax.inject.Inject;

public class BookStatusFragment extends Fragment implements BookStatusContract.View {
    private FragmentListOfBooksBinding binding;
    @Inject
    BookAdapter bookAdapter;
    @Inject
    BookStatusContract.Presenter presenter;

    public static BookStatusFragment newInstance (Book.BookStatus status) {
        Bundle bundle = new Bundle();
        bundle.putString("status", BookStatusConverter.fromStatusToString(status));
        BookStatusFragment fragment = new BookStatusFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentListOfBooksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart(BookStatusConverter.fromStringToStatus(getArguments().getString("status")));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvBooks.setLayoutManager(layoutManager);
        binding.rvBooks.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
        bookAdapter.setOnEditClickListener(book -> presenter.editBook(book));
    }

    @Override
    public void hideList() {
        binding.rvBooks.setVisibility(View.GONE);
        binding.llEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList(List<Book> booksList) {
        binding.rvBooks.setVisibility(View.VISIBLE);
        binding.llEmptyList.setVisibility(View.GONE);
        bookAdapter.setList(booksList);
    }
}
