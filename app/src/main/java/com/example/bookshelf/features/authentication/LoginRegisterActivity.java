package com.example.bookshelf.features.authentication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.bookshelf.databinding.ActivityLoginRegisterBinding;
import com.example.bookshelf.features.authentication.login.LoginFragment;
import com.example.bookshelf.features.authentication.register.RegisterFragment;

public class LoginRegisterActivity extends AppCompatActivity {
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginRegisterBinding binding = ActivityLoginRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pager = binding.fragmentContainer;
        loadFragment();
    }

    private void loadFragment() {
        AuthenticationPagerAdapter adapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment());
        adapter.addFragment(new RegisterFragment());
        pager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}
