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
import com.example.bookshelf.database.Book;
import com.example.bookshelf.databinding.FragmentListOfBooksBinding;

import java.util.List;

import javax.inject.Inject;

public class ListOfBooksFragment extends Fragment implements ListOfBooksContract.View {
    private FragmentListOfBooksBinding binding;
    @Inject
    BookAdapter bookAdapter;
    @Inject
    ListOfBooksContract.Presenter presenter;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentListOfBooksBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        presenter.onStart();
        return view;
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
