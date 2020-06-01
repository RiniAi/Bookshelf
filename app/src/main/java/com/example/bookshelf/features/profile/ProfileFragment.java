package com.example.bookshelf.features.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    public static final int CHOOSE_IMAGE = 101;
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

        initAdapter();
        initStartProfileView();
        initButtons();
        presenter.onStart();
        return binding.getRoot();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvStatisticsBookChallenge.setLayoutManager(layoutManager);
        binding.rvStatisticsBookChallenge.setAdapter(bookChallengeAdapter);
    }

    @Override
    public void initStartProfileView() {
        binding.btnSave.setVisibility(View.GONE);
        binding.tvChangeAvatar.setVisibility(View.GONE);
        binding.btnChangePassword.setVisibility(View.GONE);
        binding.pbSaveProfileInformation.setVisibility(View.GONE);
        binding.etName.setEnabled(false);
        binding.etEmail.setEnabled(false);
        binding.userAvatar.setEnabled(false);
        binding.userAvatar.setAlpha(1f);
    }

    private void initButtons() {
        binding.btnLogOut.setOnClickListener(view -> presenter.signOut());
        binding.btnEdit.setOnClickListener(view -> presenter.editProfile());
        binding.userAvatar.setOnClickListener(view -> showImageChooser());
        binding.btnSave.setOnClickListener(view -> saveProfileInformation());
    }

    @Override
    public void initProfileViewForEditButton() {
        binding.btnSave.setVisibility(View.VISIBLE);
        binding.tvChangeAvatar.setVisibility(View.VISIBLE);
        binding.etName.setEnabled(true);
        binding.userAvatar.setEnabled(true);
        binding.etName.requestFocus();
        binding.userAvatar.setAlpha(0.5f);
    }

    private void showImageChooser() {
        presenter.openGallery();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.setProfileImage(requestCode, resultCode, data);
    }

    @Override
    public void setProfileImage(Uri uriProfileImage) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uriProfileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        binding.userAvatar.setImageBitmap(bitmap);
    }

    private void saveProfileInformation() {
        binding.btnEdit.setVisibility(View.GONE);
        binding.pbSaveProfileInformation.setVisibility(View.VISIBLE);
        if (binding.etName.getText().toString().isEmpty()) {
            binding.etName.setError("Name required");
            binding.etName.requestFocus();
            binding.btnEdit.setVisibility(View.VISIBLE);
            binding.pbSaveProfileInformation.setVisibility(View.GONE);
            return;
        }
        presenter.saveUserInformation(binding.etName.getText().toString());
        initProfileViewForFinishSave();
    }

    private void initProfileViewForFinishSave() {
        binding.btnSave.setVisibility(View.GONE);
        binding.tvChangeAvatar.setVisibility(View.GONE);
        binding.btnChangePassword.setVisibility(View.GONE);
        binding.etName.setEnabled(false);
        binding.etEmail.setEnabled(false);
    }

    @Override
    public void getProfileImage(String profileImage) {
        Picasso.get().load(profileImage).placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image).into(binding.userAvatar);
    }

    @Override
    public void getProfileInformation(String name, String email) {
        binding.etName.setText(name);
        binding.etEmail.setText(email);
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

    @Override
    public void updateProfile() {
        Toast.makeText(getActivity(), "Update profile", Toast.LENGTH_SHORT).show();
        binding.userAvatar.setAlpha(1f);
        binding.btnEdit.setVisibility(View.VISIBLE);
        binding.pbSaveProfileInformation.setVisibility(View.GONE);
    }

    @Override
    public void openGallery(Intent intent) {
        startActivityForResult(Intent.createChooser(intent, "Select profile image"), CHOOSE_IMAGE);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Data could not be updated. Try again later!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorConnection() {
        binding.userAvatar.setAlpha(1f);
        binding.btnEdit.setVisibility(View.VISIBLE);
        binding.pbSaveProfileInformation.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Check your Internet connection!", Toast.LENGTH_SHORT).show();
    }
}
