package com.example.bookshelf.features.bookchallenge;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.databinding.ActivityBookChallengeBinding;

import java.util.List;

import javax.inject.Inject;

public class BookChallengeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, BookChallengeContract.View {
    private ActivityBookChallengeBinding binding;
    @Inject
    BookChallengeAdapter bookAdapter;
    @Inject
    BookChallengeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = ActivityBookChallengeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        updateToolbar();
        buildRecyclerView();
        setSupportActionBar(binding.toolbarBookChallenge.toolbar);
        presenter.onStart();
    }

    private void updateToolbar() {
        binding.toolbarBookChallenge.toolbar.setTitle(R.string.book_challenge_title);
    }

    private void buildRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(BookChallengeActivity.this, 2);
        binding.rvBooks.setLayoutManager(layoutManager);
        binding.rvBooks.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
    }

    @Override
    public void changeCounter(String counter) {
        binding.tvCounter.setText(counter);
    }

    @Override
    public void changeCounterForBar(int counter) {
        binding.sbCounter.setOnSeekBarChangeListener(this);
        binding.sbCounter.setProgress(counter);
    }

    @Override
    public void showList(List<Book> books) {
        bookAdapter.setList(books);
    }

    @Override
    public void changeProgress(String progress) {
        binding.tvProgress.setText(progress);
    }

    @Override
    public void showCounterSavedMessage() {
        Toast.makeText(BookChallengeActivity.this, R.string.book_challenge_save_counter, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int counter, boolean fromUser) {
        presenter.onProgressChanged(counter);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        presenter.saveCounter(binding.tvCounter.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_book_challenge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_main_activity:
                presenter.openMain();
                break;
        }
        return true;
    }
}
