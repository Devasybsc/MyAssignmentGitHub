package com.example.myassignmentgithub

import com.example.myassignmentgithub.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GitHubUserListApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().build()
    }
}
