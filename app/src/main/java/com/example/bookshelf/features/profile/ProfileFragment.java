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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private static final int CHOOSE_IMAGE = 101;
    private FragmentProfileBinding binding;
    private Uri uriProfileImage;
    private FirebaseAuth firebaseAuth;
    private String profileImage;
    private FirebaseUser userFirebase;
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

        initFirebase();
        initProfileView();
        initAdapter();
        initButtons();
        presenter.onStart();
        return binding.getRoot();
    }
    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        userFirebase = firebaseAuth.getCurrentUser();
    }

    private void initProfileView() {
        binding.etName.setText(userFirebase.getDisplayName());
        binding.etEmail.setText(userFirebase.getEmail());
        if (userFirebase.getPhotoUrl() != null) {
            Picasso.get().load(userFirebase.getPhotoUrl().toString()).placeholder(R.drawable.ic_broken_image)
                    .error(R.drawable.ic_broken_image).into(binding.userAvatar);
        }
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvStatisticsBookChallenge.setLayoutManager(layoutManager);
        binding.rvStatisticsBookChallenge.setAdapter(bookChallengeAdapter);
    }

    private void initButtons() {
        binding.etName.setEnabled(false);
        binding.etEmail.setEnabled(false);
        binding.userAvatar.setEnabled(false);
        binding.btnSave.setVisibility(View.GONE);
        binding.btnChangePassword.setVisibility(View.GONE);
        binding.tvChangeAvatar.setVisibility(View.GONE);
        binding.btnLogOut.setOnClickListener(view -> {
            firebaseAuth.signOut();
            presenter.openAuthentication();
        });
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnSave.setVisibility(View.VISIBLE);
                binding.tvChangeAvatar.setVisibility(View.VISIBLE);
                binding.userAvatar.setAlpha(0.5f);
                binding.etName.setEnabled(true);
                binding.etName.requestFocus();
                binding.userAvatar.setEnabled(true);
            }
        });

        binding.userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (binding.etName.getText().toString().isEmpty()) {
                    binding.etName.setError("Name required");
                    binding.etName.requestFocus();
                    return;
                }
                saveUserInformation();
                finishSave();
            }
        });
    }

    private void finishSave() {
        binding.btnSave.setVisibility(View.GONE);
        binding.tvChangeAvatar.setVisibility(View.GONE);
        binding.etName.setEnabled(false);
        binding.etEmail.setEnabled(false);
        binding.btnChangePassword.setVisibility(View.GONE);
    }

    private void saveUserInformation() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/" + userFirebase.getUid() + ".jpg");
        if (uriProfileImage != null) {
            storageReference.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            profileImage = uri.toString();
                            saveProfile();
                        }
                    });
                }
            });
        }
    }

    private void saveProfile() {
        if (!profileImage.isEmpty()) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(binding.etName.getText().toString())
                    .setPhotoUri(Uri.parse(profileImage))
                    .build();

            userFirebase.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Update profile ", Toast.LENGTH_SHORT).show();
                        binding.userAvatar.setAlpha(1f);
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uriProfileImage);
                binding.userAvatar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select profile image"), CHOOSE_IMAGE);
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
