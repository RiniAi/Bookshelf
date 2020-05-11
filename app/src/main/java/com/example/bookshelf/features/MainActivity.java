package com.example.bookshelf.features;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bookshelf.R;
import com.example.bookshelf.features.bookchallenge.BookChallengeFragment;
import com.example.bookshelf.features.bookssearch.SearchFragment;
import com.example.bookshelf.features.listofbooks.ListsOfBooksFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelected);
        navigation.setSelectedItemId(R.id.challenge);
        loadFragment(new BookChallengeFragment());
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelected
            = item -> {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.challenge:
                if (navigation.getSelectedItemId() == R.id.challenge) {
                    getSupportFragmentManager().popBackStack();
                }
                fragment = new BookChallengeFragment();
                break;
            case R.id.main:
                if (navigation.getSelectedItemId() == R.id.main) {
                    getSupportFragmentManager().popBackStack();
                }
                fragment = new ListsOfBooksFragment();
                break;
            case R.id.search:
                if (navigation.getSelectedItemId() == R.id.search) {
                    getSupportFragmentManager().popBackStack();
                }
                fragment = new SearchFragment();
                break;
        }
        return loadFragment(fragment);
    };
}
