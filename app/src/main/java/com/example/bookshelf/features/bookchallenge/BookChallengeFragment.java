package com.example.bookshelf.features.bookchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.databinding.FragmentBookChallengeBinding;
import com.example.bookshelf.features.bookabout.AboutBookFragment;

import java.util.List;

import javax.inject.Inject;

public class BookChallengeFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, BookChallengeContract.View {
    private FragmentBookChallengeBinding binding;
    @Inject
    BookChallengeAdapter bookAdapter;
    @Inject
    BookChallengeContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentBookChallengeBinding.inflate(inflater, container, false);
        presenter.onStart();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvBooks.setLayoutManager(layoutManager);
        binding.rvBooks.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(book -> presenter.openBook(book));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.saveOrUpdateCounterAndProgress();
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
    public void hideList() {
        binding.rvBooks.setVisibility(View.GONE);
        binding.llEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList(List<Book> books) {
        binding.rvBooks.setVisibility(View.VISIBLE);
        binding.llEmptyList.setVisibility(View.GONE);
        bookAdapter.setList(books);
    }

    @Override
    public void changeProgress(String progress) {
        binding.tvProgress.setText(progress);
    }

    @Override
    public void showCounterSavedMessage() {
        Toast.makeText(getActivity(), R.string.book_challenge_save_counter, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openBook(AboutBookFragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
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
}
