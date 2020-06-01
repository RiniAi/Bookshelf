package com.example.bookshelf.features.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
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

import java.util.List;

import javax.inject.Inject;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 110;
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
        hideProgressBar();
        hideSaveButton();
    }

    private void hideSaveButton() {
        binding.btnSave.setVisibility(View.GONE);
        binding.tvChangeAvatar.setVisibility(View.GONE);
        binding.btnChangePassword.setVisibility(View.GONE);
        binding.etName.setEnabled(false);
        binding.etEmail.setEnabled(false);
        binding.userAvatar.setEnabled(false);
    }

    private void showProgressBar() {
        binding.btnEdit.setVisibility(View.GONE);
        binding.pbSaveProfileInformation.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.userAvatar.setAlpha(1f);
        binding.btnEdit.setVisibility(View.VISIBLE);
        binding.pbSaveProfileInformation.setVisibility(View.GONE);
    }

    private void initButtons() {
        binding.btnLogOut.setOnClickListener(view -> presenter.signOut());
        binding.btnEdit.setOnClickListener(view -> presenter.editProfile());
        binding.userAvatar.setOnClickListener(view -> {
            checkPermission();
        });
        binding.btnSave.setOnClickListener(view -> saveProfileInformation());
    }

    private void checkPermission() {
        if (checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            }
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            return;
        } else {
            presenter.openGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = shouldShowRequestPermissionRationale(permission);
                    if (!showRationale) {
                        binding.tvChangeAvatar.setVisibility(View.GONE);
                        binding.userAvatar.setAlpha(1f);
                        binding.userAvatar.setEnabled(false);
                        Toast.makeText(getActivity(), "You have disabled permission to read and write data. You can enable permission in the app settings",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    presenter.openGallery();
                }
            }
        }
    }

    @Override
    public void initProfileViewForEditButton() {
        binding.btnSave.setVisibility(View.VISIBLE);
        binding.tvChangeAvatar.setVisibility(View.VISIBLE);
        binding.btnChangePassword.setVisibility(View.VISIBLE);
        binding.etName.setEnabled(true);
        binding.userAvatar.setEnabled(true);
        binding.etName.requestFocus();
        binding.userAvatar.setAlpha(0.5f);
    }

    @Override
    public void openGallery(Intent intent) {
        startActivityForResult(Intent.createChooser(intent, "Select profile image"), CHOOSE_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.setProfileImage(requestCode, resultCode, data);
    }

    @Override
    public void setProfileImage(Bitmap profileImage) {
        binding.userAvatar.setImageBitmap(profileImage);
    }

    private void saveProfileInformation() {
        if (binding.etName.getText().toString().isEmpty()) {
            binding.etName.setError("Name required");
            binding.etName.requestFocus();
            hideProgressBar();
            return;
        }
        showProgressBar();
        presenter.saveUserInformation(binding.etName.getText().toString());
        hideSaveButton();
    }

    @Override
    public void getProfileImage(String profileImage) {
        Picasso.get().load(profileImage)
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(binding.userAvatar);
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
        hideProgressBar();
        Toast.makeText(getActivity(), "Update profile", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        hideProgressBar();
        Toast.makeText(getActivity(), "Data could not be updated. Try again later!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorConnection() {
        hideProgressBar();
        Toast.makeText(getActivity(), "Check your Internet connection!", Toast.LENGTH_SHORT).show();
    }
}
