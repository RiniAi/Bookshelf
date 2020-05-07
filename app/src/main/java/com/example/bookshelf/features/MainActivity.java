package com.example.bookshelf.features;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bookshelf.R;
import com.example.bookshelf.features.bookchallenge.BookChallengeFragment;
import com.example.bookshelf.features.bookssearch.SearchFragment;
import com.example.bookshelf.features.listofbooks.ListOfBooksFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelected);
        navigation.setSelectedItemId(R.id.main);
        loadFragment(new ListOfBooksFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null) //чтобы открыть пред. фрагмент, а не закрыть приложение
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
                        fragment = new BookChallengeFragment();
                        break;
                    case R.id.main:
                        fragment = new ListOfBooksFragment();
                        break;
                    case R.id.search:
                        fragment = new SearchFragment();
                        break;
                }
                return loadFragment(fragment);
            };
}
