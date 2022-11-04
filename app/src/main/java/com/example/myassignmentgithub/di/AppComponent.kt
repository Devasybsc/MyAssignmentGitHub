package com.example.myassignmentgithub.di

import com.example.myassignmentgithub.GitHubUserListApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UtilModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<GitHubUserListApplication> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}