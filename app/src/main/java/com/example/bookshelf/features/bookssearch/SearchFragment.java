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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.databinding.FragmentSearchBinding;
import com.example.bookshelf.features.bookabout.AboutBookFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.bookshelf.features.bookssearch.BookSearchAdapter.VIEW_TYPE_ITEM;
import static com.example.bookshelf.features.bookssearch.BookSearchAdapter.VIEW_TYPE_LOADING;
import static com.example.bookshelf.network.GoogleBooksApiService.QUERY_COUNTER;

public class SearchFragment extends Fragment implements SearchContract.View {
    private FragmentSearchBinding binding;
    private List<Book> lists;
    private boolean isLoading;
    private int startIndex;

    @Inject
    BookSearchAdapter bookAdapter;
    @Inject
    SearchContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        showListEmpty();
        initAdapter();
        initScrollListener();
        initLayoutManager();
        buildButtons();
        return binding.getRoot();
    }

    private void initAdapter() {
        binding.rvOfBooks.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
        bookAdapter.setOnEditClickListener(book -> presenter.editBook(book));
    }

    private void initScrollListener() {
        binding.rvOfBooks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == lists.size() - 1 - QUERY_COUNTER / 2) {
                        lists.add(null);
                        presenter.searchBook(binding.toolbarSearchFragment.etQuery.getText().toString(), startIndex);
                    }
                }
            }
        });
    }

    private void initLayoutManager() {
        GridLayoutManager layoutManager = (GridLayoutManager) binding.rvOfBooks.getLayoutManager();
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (bookAdapter.getItemViewType(position)) {
                    case VIEW_TYPE_ITEM:
                        return 1;
                    case VIEW_TYPE_LOADING:
                        return 2; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });
    }

    private void buildButtons() {
        binding.toolbarSearchFragment.ibSendQuery.setOnClickListener(view -> {
            clear();
            showDownloadSign();
            presenter.searchBook(binding.toolbarSearchFragment.etQuery.getText().toString(), startIndex);
            hideKeyboard(getActivity(), view);
        });

        binding.toolbarSearchFragment.etQuery.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                clear();
                showDownloadSign();
                presenter.searchBook(binding.toolbarSearchFragment.etQuery.getText().toString(), startIndex);
                hideKeyboard(getActivity(), view);
                return true;
            }
            return false;
        });
    }

    private void clear() {
        lists = new ArrayList<>();
        startIndex = 0;
        isLoading = false;
        bookAdapter.notifyItemRangeRemoved(0, lists.size());
    }

    private static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showBooks(List<Book> bookList) {
        showListView();
        startIndex += 40;
        if (!lists.isEmpty()) {
            lists.remove(lists.size() - 1);
        }
        lists.addAll(bookList);
        isLoading = bookList.size() < QUERY_COUNTER;
        if (bookList.size() <= QUERY_COUNTER) {
            bookAdapter.setList(lists);
        } else {
            bookAdapter.notifyItemInserted(bookAdapter.getItemCount() - bookList.size());
        }
    }

    @Override
    public void showError() {
        binding.progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), R.string.search_activity_try_later, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage() {
        showListEmpty();
        Toast.makeText(getActivity(), R.string.search_activity_enter_query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyView() {
        showListEmpty();
        Toast.makeText(getActivity(), R.string.search_activity_nothing_was_found, Toast.LENGTH_SHORT).show();
    }


    private void showListView() {
        binding.llEmptyList.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.rvOfBooks.setVisibility(View.GONE);
    }

    private void showListEmpty() {
        binding.rvOfBooks.setVisibility(View.VISIBLE);
        binding.llEmptyList.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showDownloadSign() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.llEmptyList.setVisibility(View.GONE);
        binding.rvOfBooks.setVisibility(View.GONE);
    }

    @Override
    public void openBook(AboutBookFragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        poppingKeyboard();
        if (lists != null) {
            showListView();
            bookAdapter.setList(lists);
        }
    }

    private void poppingKeyboard() {
        if (binding.toolbarSearchFragment.etQuery.getText().toString().equals("")) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
        binding.toolbarSearchFragment.etQuery.requestFocus();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }
}
