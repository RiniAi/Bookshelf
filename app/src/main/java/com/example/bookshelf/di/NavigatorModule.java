package com.example.bookshelf.di;

import android.content.Context;

import com.example.bookshelf.Navigator;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigatorModule {
    @Provides
    public Navigator providesNavigator(Context context){
        return new Navigator(context);
    }
}
