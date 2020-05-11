package com.example.bookshelf.features.bookssearch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.databinding.FragmentSearchBinding;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends Fragment implements SearchContract.View {
    private FragmentSearchBinding binding;
    @Inject
    BookSearchAdapter bookAdapter;
    @Inject
    SearchContract.Presenter presenter;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        binding.llEmptyList.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        buildButtons();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvOfBooks.setLayoutManager(layoutManager);
        binding.rvOfBooks.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
        bookAdapter.setOnEditClickListener(book -> presenter.editBook(book));
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    private void buildButtons() {
        binding.ibSendQuery.setOnClickListener(view -> {
            hideList();
            presenter.searchBook(binding.etQuery.getText().toString());
            hideKeyboard(getActivity(), view);
        });

        binding.etQuery.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideList();
                presenter.searchBook(binding.etQuery.getText().toString());
                hideKeyboard(getActivity(), view);
                return true;
            }
            return false;
        });
    }

    private void hideList() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rvOfBooks.setVisibility(View.GONE);
        binding.llEmptyList.setVisibility(View.GONE);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showBooks(List<Book> bookList) {
        binding.progressBar.setVisibility(View.GONE);
        binding.llEmptyList.setVisibility(View.GONE);
        binding.rvOfBooks.setVisibility(View.VISIBLE);
        bookAdapter.setList(bookList);
    }

    @Override
    public void showError() {
        binding.progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), R.string.search_activity_try_later, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage() {
        binding.progressBar.setVisibility(View.GONE);
        binding.llEmptyList.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), R.string.search_activity_enter_query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyView() {
        binding.progressBar.setVisibility(View.GONE);
        binding.llEmptyList.setVisibility(View.VISIBLE);
        binding.rvOfBooks.setVisibility(View.GONE);
        Toast.makeText(getActivity(), R.string.search_activity_nothing_was_found, Toast.LENGTH_SHORT).show();
    }
}


