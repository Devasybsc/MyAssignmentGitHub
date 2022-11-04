package com.example.myassignmentgithub.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class UtilModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
