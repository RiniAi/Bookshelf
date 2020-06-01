package com.example.bookshelf.features.profile;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;
import com.example.bookshelf.usecases.SearchListOfBookChallengeUseCase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;
import static com.example.bookshelf.features.profile.ProfileFragment.CHOOSE_IMAGE;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser userFirebase;
    private String profileImage;
    private Uri uriProfileImage;

    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;
    @Inject
    SearchListOfBookChallengeUseCase searchListOfBookChallengeUseCase;
    @Inject
    Navigator navigator;
    @Inject
    Context context;

    @Inject
    public ProfilePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, SearchListOfBookChallengeUseCase searchListOfBookChallengeUseCase,
                            Navigator navigator, Context context) {
        this.searchBookWithStatusUseCase = searchBookWithStatusUseCase;
        this.searchListOfBookChallengeUseCase = searchListOfBookChallengeUseCase;
        this.navigator = navigator;
        this.context = context;
    }

    @Override
    public void onStart() {
        initFirebase();
        initProfileView();
        countNumberBooks();
        loadStatisticsBookChallenge();
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        userFirebase = firebaseAuth.getCurrentUser();
    }

    @Override
    public void initProfileView() {
        if (userFirebase.getPhotoUrl() != null) {
            view.getProfileImage(userFirebase.getPhotoUrl().toString());
        }
        view.getProfileInformation(userFirebase.getDisplayName(), userFirebase.getEmail());
    }

    private void countNumberBooks() {
        view.setCountNumberBooks(
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.IN_THE_PROCESS_OF_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.PLAN_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.FINISH_READING).size())),
                (Integer.toString(searchBookWithStatusUseCase.run(Book.BookStatus.QUIT_READING).size())));
    }

    private void loadStatisticsBookChallenge() {
        view.loadStatisticsBookChallenge(searchListOfBookChallengeUseCase.run());
    }

    @Override
    public void saveUserInformation(String name) {
        if (isOnline()) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/" + userFirebase.getUid() + ".jpg");
            if (uriProfileImage != null) {
                storageReference.putFile(uriProfileImage)
                        .addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    profileImage = uri.toString();
                                    saveProfile(name);
                                }).addOnFailureListener(e -> view.showError())
                        ).addOnFailureListener(e -> view.showError());
            }
        } else {
            view.showErrorConnection();
        }
    }

    private void saveProfile(String name) {
        UserProfileChangeRequest profile;
        if (!profileImage.isEmpty()) {
            profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .setPhotoUri(Uri.parse(profileImage))
                    .build();
        } else {
            profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build();
        }
        userFirebase.updateProfile(profile).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                view.updateProfile();
            } else {
                view.showError();
            }
        });
    }

    @Override
    public void setProfileImage(int requestCode, int resultCode, Intent data) {
        if (isOnline()) {
            if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                uriProfileImage = data.getData();
                view.setProfileImage(uriProfileImage);
            }
        } else {
            view.showErrorConnection();
        }
    }

    @Override
    public void editProfile() {
        if (isOnline()) {
            view.initProfileViewForEditButton();
        } else {
            view.showErrorConnection();
        }
    }

    @Override
    public void signOut() {
        firebaseAuth.signOut();
        navigator.openAuthentication();
    }

    @Override
    public void openGallery() {
        view.openGallery(navigator.openGallery());
    }

    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) context.
                getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
