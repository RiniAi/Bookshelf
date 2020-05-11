package com.example.bookshelf.features.listofbooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.bookshelf.R;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.databinding.FragmentListsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListsOfBooksFragment extends Fragment {
    private FragmentListsBinding binding;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentListsBinding.inflate(inflater, container, false);
        BottomNavigationView navigation = binding.navView;
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelected);
        navigation.setSelectedItemId(R.id.finish);
        loadFragment(BookStatusFragment.newInstance(Book.BookStatus.FINISH_READING));
        return binding.getRoot();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelected
            = item -> {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.process:
                fragment = BookStatusFragment.newInstance(Book.BookStatus.IN_THE_PROCESS_OF_READING);
                break;
            case R.id.plan:
                fragment = BookStatusFragment.newInstance(Book.BookStatus.PLAN_READING);
                break;
            case R.id.finish:
                fragment = BookStatusFragment.newInstance(Book.BookStatus.FINISH_READING);
                break;
            case R.id.quit:
                fragment = BookStatusFragment.newInstance(Book.BookStatus.QUIT_READING);
                break;
        }
        return loadFragment(fragment);
    };
}
