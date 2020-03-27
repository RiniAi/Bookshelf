package com.example.bookshelf.di.main;

import com.example.bookshelf.features.main.MainActivity;

import dagger.Subcomponent;

@Subcomponent
public interface MainActivityComponent {
    void inject (MainActivity mainActivity);
}
