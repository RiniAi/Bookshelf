package com.example.bookshelf.features.authentication.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.databinding.FragmentLoginBinding;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements LoginContract.View {
    private FragmentLoginBinding binding;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        showView();
        initButtons();
        return binding.getRoot();
    }

    private void initButtons() {
        binding.btnLogin.setOnClickListener(view -> {
            showProgressBar();
            presenter.start(binding.etEmail.getText().toString(),
                    binding.etPassword.getText().toString());
            hideKeyboard(getActivity(), view);
        });
    }

    @Override
    public void checkDate() {
        Toast.makeText(getActivity(), R.string.register_fragment_check_date, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorEmptyFields(String fields) {
        switch (fields) {
            case "email":
                binding.tvEmptyEmail.setText(R.string.register_fragment_field_empty);
                break;
            case "password":
                binding.tvEmptyPassword.setText(R.string.register_fragment_field_empty);
                break;
        }
    }

    @Override
    public void showErrorInvalidFields() {
        binding.tvEmptyEmail.setText(R.string.register_fragment_email_invalid);
    }

    @Override
    public void hintErrorFields(String fields) {
        switch (fields) {
            case "email":
                binding.tvEmptyEmail.setText(R.string.empty_string);
                break;
            case "password":
                binding.tvEmptyPassword.setText(R.string.empty_string);
                break;
        }
    }

    @Override
    public void successfulLogin() {
        hintErrorField();
        showEmptyField();
        presenter.openMain();
        Toast.makeText(getActivity(), R.string.login_fragment_login_was_successful, Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorLogin(Exception exception) {
        Toast.makeText(getActivity(), "Authentication failed" + "\n" + exception.getMessage(), Toast.LENGTH_LONG).show();
        showView();
    }

    private static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showView() {
        binding.llCreateAccount.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        binding.llCreateAccount.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hintErrorField() {
        binding.tvEmptyEmail.setText(R.string.empty_string);
        binding.tvEmptyPassword.setText(R.string.empty_string);
    }

    private void showEmptyField() {
        binding.etEmail.setText(R.string.empty_string);
        binding.etPassword.setText(R.string.empty_string);
    }
}
