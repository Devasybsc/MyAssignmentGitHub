package com.example.myassignmentgithub.di

import com.example.myassignmentgithub.MainActivity
import com.example.myassignmentgithub.ui.SearchUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSearchUsersFragment(): SearchUsersFragment

}
