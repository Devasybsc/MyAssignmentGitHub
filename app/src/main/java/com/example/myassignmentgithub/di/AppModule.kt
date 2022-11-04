package com.example.myassignmentgithub.di

import com.example.myassignmentgithub.MainActivity
import com.example.myassignmentgithub.SearchUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSearchUsersFragment(): SearchUsersFragment

}
