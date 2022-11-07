package com.example.myassignmentgithub.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.myassignmentgithub.datasource.PreferenceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class AppModule(
    @get:Provides
    val application: Application
) {
    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun providePreferenceProvider(application: Application): PreferenceProvider {
        return PreferenceProvider(application)
    }
}
