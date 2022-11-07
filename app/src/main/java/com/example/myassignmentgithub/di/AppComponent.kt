package com.example.myassignmentgithub.di

import android.app.Application
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
        DataSourceModule::class,
        RepositoryModule::class,
        UtilModule::class,
        ViewModelModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<GitHubUserListApplication> {

    override fun inject(application: GitHubUserListApplication)
    fun application(): Application

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}