package com.example.myassignmentgithub.di

import android.app.Application
import android.content.Context
import com.example.myassignmentgithub.datasource.PreferenceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule (private val application: Application) {  // to allow abstract method make module abstract



    @Provides
     fun provideApplication(): Application = application

//        @Provides
//    @Singleton
//    fun providePreferenceProvider(application : Application): PreferenceProvider {
//        return PreferenceProvider(application)
//    }
}