package com.example.bookshelf.features.authentication.register;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.bookshelf.databinding.FragmentRegisterBinding;

import javax.inject.Inject;

public class RegisterFragment extends Fragment implements RegisterContract.View {
    private FragmentRegisterBinding binding;

    @Inject
    RegisterContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        showView();
        initButtons();
        return binding.getRoot();
    }

    public void onStart() {
        super.onStart();
        presenter.checkSignUser();
    }

    private void initButtons() {
        binding.btnRegister.setOnClickListener(view -> {
            showProgressBar();
            presenter.start(binding.etName.getText().toString(),
                    binding.etEmail.getText().toString(),
                    binding.etPassword.getText().toString(),
                    binding.etRepassword.getText().toString());
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
            case "name":
                binding.tvEmptyName.setText(R.string.register_fragment_field_empty);
                break;
            case "email":
                binding.tvEmptyEmail.setText(R.string.register_fragment_field_empty);
                break;
            case "password":
                binding.tvEmptyPassword.setText(R.string.register_fragment_field_empty);
                break;
            case "rePassword":
                binding.tvEmptyRepassword.setText(R.string.register_fragment_field_empty);
                break;
        }
    }

    @Override
    public void showErrorInvalidFields(String fields) {
        switch (fields) {
            case "email":
                binding.tvEmptyEmail.setText(R.string.register_fragment_email_invalid);
                break;
            case "password":
                binding.tvEmptyPassword.setText(R.string.register_fragment_password_invalid);
                break;
            case "rePassword":
                binding.tvEmptyRepassword.setText(R.string.register_fragment_passwords_not_match);
                break;
        }
    }

    @Override
    public void showErrorLengthPassword() {
        binding.tvEmptyPassword.setText(getString(R.string.register_fragment_password_less));
    }

    @Override
    public void hintErrorFields(String fields) {
        switch (fields) {
            case "name":
                binding.tvEmptyName.setText(R.string.empty_string);
                break;
            case "email":
                binding.tvEmptyEmail.setText(R.string.empty_string);
                break;
            case "password":
                binding.tvEmptyPassword.setText(R.string.empty_string);
                break;
            case "rePassword":
                binding.tvEmptyRepassword.setText(R.string.empty_string);
                break;
        }
    }

    @Override
    public void successfulRegistration() {
        hintErrorField();
        showEmptyField();
        presenter.openMain();
        Toast.makeText(getActivity(), R.string.register_fragment_registration_was_successful, Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorRegistration(Exception exception) {
        Toast.makeText(getActivity(), "Authentication failed" + "\n" + exception.getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    public SharedPreferences initSharedPreferences() {
        return getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
    }

    private void showProgressBar() {
        binding.llCreateAccount.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hintErrorField() {
        binding.tvEmptyName.setText(R.string.empty_string);
        binding.tvEmptyEmail.setText(R.string.empty_string);
        binding.tvEmptyPassword.setText(R.string.empty_string);
        binding.tvEmptyRepassword.setText(R.string.empty_string);
    }

    private void showEmptyField() {
        binding.etName.setText(R.string.empty_string);
        binding.etEmail.setText(R.string.empty_string);
        binding.etPassword.setText(R.string.empty_string);
        binding.etRepassword.setText(R.string.empty_string);
    }
}
