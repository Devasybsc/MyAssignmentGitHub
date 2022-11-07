package com.example.myassignmentgithub

import android.app.Application
import com.example.myassignmentgithub.di.AppComponent
import com.example.myassignmentgithub.di.AppModule
import com.example.myassignmentgithub.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GitHubUserListApplication :  DaggerApplication() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
