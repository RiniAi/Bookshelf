package com.example.bookshelf.features.listofbooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.databinding.FragmentListsBinding;
import com.google.android.material.tabs.TabLayout;

public class ListsOfBooksFragment extends Fragment {
    private TabLayout navigation;
    private ViewPager pager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentListsBinding binding = FragmentListsBinding.inflate(inflater, container, false);
        navigation = binding.navigation;
        pager = binding.fragmentContainer;
        loadFragment();
        return binding.getRoot();
    }

    private void loadFragment() {
        StatusFragmentPageAdapter adapter = new StatusFragmentPageAdapter(getChildFragmentManager());
        adapter.addFragment(BookStatusFragment.newInstance(Book.BookStatus.IN_THE_PROCESS_OF_READING), "Read");
        adapter.addFragment(BookStatusFragment.newInstance(Book.BookStatus.PLAN_READING), "Plan");
        adapter.addFragment(BookStatusFragment.newInstance(Book.BookStatus.FINISH_READING), "Finish");
        adapter.addFragment(BookStatusFragment.newInstance(Book.BookStatus.QUIT_READING), "Quit");
        pager.setAdapter(adapter);
        navigation.setupWithViewPager(pager);
    }
}
