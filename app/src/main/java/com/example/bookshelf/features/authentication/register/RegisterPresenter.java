package com.example.bookshelf.features.authentication.register;

import android.text.TextUtils;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    private FirebaseAuth firebaseAuth;
    @Inject
    Navigator navigator;

    @Inject
    public RegisterPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void start(String name, String email, String password, String rePassword) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || !isValidEmail(email) || !isValidPassword(password) || password.length() < 8 || (!password.equals(rePassword))) {
            view.showView();
            checkRePassword(password, rePassword);
            checkPassword(password);
            checkEmail(email);
            checkName(name);
        } else {
            initFireBase();
            saveUser(name, email, password);
        }
    }

    @Override
    public void openMain() {
        navigator.openMain();
    }

    private void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void saveUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        createUser(user);
    }

    private void createUser(User user) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                .setDisplayName(user.getName())
                                .build();
                        firebaseAuth.getCurrentUser().updateProfile(profile);
                        view.successfulRegistration();
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            view.showView();
                            view.errorAlreadyRegistered();
                        } else {
                            view.showView();
                            view.errorRegistration(task.getException());
                        }
                    }
                });
    }

    private void checkName(String name) {
        if (name.isEmpty()) {
            view.showErrorEmptyFields("name");
        }
    }

    private void checkEmail(String email) {
        if (email.isEmpty() || !isValidEmail(email)) {
            if (email.isEmpty()) {
                view.showErrorEmptyFields("email");
            } else if (!isValidEmail(email)) {
                view.showErrorInvalidFields("email");
            }
        }
    }

    private void checkPassword(String password) {
        if (password.isEmpty() || password.length() < 8 || !isValidPassword(password)) {
            if (password.isEmpty()) {
                view.showErrorEmptyFields("password");
            } else if (password.length() < 8 || !isValidPassword(password)) {
                view.showErrorInvalidFields("password");
            }
        }
    }

    private void checkRePassword(String password, String rePassword) {
        if (rePassword.isEmpty() || !(password.equals(rePassword))) {
            if (rePassword.isEmpty()) {
                view.showErrorEmptyFields("rePassword");
            } else {
                view.showErrorInvalidFields("rePassword");
            }
        }
    }

    private static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private static boolean isValidPassword(final String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
