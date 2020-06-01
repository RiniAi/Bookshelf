package com.example.bookshelf.features.profile;

import android.content.Intent;
import android.net.Uri;

import com.example.bookshelf.database.bookChallenge.BookChallenge;

import java.util.List;

public interface ProfileContract {
    interface View {
        void getProfileImage(String profileImage);

        void getProfileInformation(String name, String email);

        void setCountNumberBooks(String numberBooksProgress, String numberBooksPlan, String numberBooksRead, String numberBooksQuit);

        void loadStatisticsBookChallenge(List<BookChallenge> run);

        void updateProfile();

        void initStartProfileView();

        void initProfileViewForEditButton();

        void setProfileImage(Uri uriProfileImage);

        void openGallery(Intent intent);

        void showError();

        void showErrorConnection();
    }

    interface Presenter {
        void onStart();

        void signOut();

        void initProfileView();

        void saveUserInformation(String name);

        void openGallery();

        void setProfileImage(int requestCode, int resultCode, Intent data);

        void editProfile();
    }
}
