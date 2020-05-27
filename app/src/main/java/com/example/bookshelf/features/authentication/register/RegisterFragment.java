package com.example.bookshelf.features.authentication.register;

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
    public void showErrorEmptyFields(String fields) {
        switch (fields) {
            case "name":
                binding.etName.setError(getString(R.string.register_fragment_field_empty));
                binding.etName.requestFocus();
                break;
            case "email":
                binding.etEmail.setError(getString(R.string.register_fragment_field_empty));
                binding.etEmail.requestFocus();
                break;
            case "password":
                binding.etPassword.setError(getString(R.string.register_fragment_field_empty));
                binding.etPassword.requestFocus();
                break;
            case "rePassword":
                binding.etRepassword.setError(getString(R.string.register_fragment_field_empty));
                binding.etRepassword.requestFocus();
                break;
        }
    }

    @Override
    public void showErrorInvalidFields(String fields) {
        switch (fields) {
            case "email":
                binding.etEmail.setError(getString(R.string.register_fragment_email_invalid));
                binding.etEmail.requestFocus();
                break;
            case "password":
                binding.etPassword.setError(getString(R.string.register_fragment_password_invalid));
                binding.etPassword.requestFocus();
                break;
            case "rePassword":
                binding.etRepassword.setError(getString(R.string.register_fragment_passwords_not_match));
                binding.etRepassword.requestFocus();
                break;
        }
    }

    @Override
    public void successfulRegistration() {
        presenter.openMain();
        Toast.makeText(getActivity(), R.string.register_fragment_registration_was_successful, Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorRegistration(Exception exception) {
        Toast.makeText(getActivity(), "Authentication failed" + "\n" + exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorAlreadyRegistered() {
        Toast.makeText(getActivity(), R.string.register_fragment_already_registered, Toast.LENGTH_LONG).show();
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
}
