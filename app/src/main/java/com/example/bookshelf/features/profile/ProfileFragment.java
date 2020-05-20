package com.example.bookshelf.features.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.databinding.FragmentProfileBinding;

import java.util.List;

import javax.inject.Inject;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private FragmentProfileBinding binding;
    @Inject
    ProfileContract.Presenter presenter;
    @Inject
    StatisticsBookChallengeAdapter bookChallengeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.toolbarProfileFragment.toolbar.setTitle(getString(R.string.profile_fragment_information_about_profile));
        presenter.onStart();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvStatisticsBookChallenge.setLayoutManager(layoutManager);
        binding.rvStatisticsBookChallenge.setAdapter(bookChallengeAdapter);
    }

    @Override
    public void setCountNumberBooks(String numberBooksProgress, String numberBooksPlan, String numberBooksRead, String numberBooksQuit) {
        binding.tvNumberOfBooksInProgress.setText(numberBooksProgress);
        binding.tvNumberOfBooksInPlan.setText(numberBooksPlan);
        binding.tvNumberOfBooksInFinish.setText(numberBooksRead);
        binding.tvNumberOfBooksInQuit.setText(numberBooksQuit);
    }

    @Override
    public void loadStatisticsBookChallenge(List<BookChallenge> listsBookChallenge) {
        bookChallengeAdapter.setList(listsBookChallenge);
    }
}
