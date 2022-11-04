package com.example.myassignmentgithub.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myassignmentgithub.viewmodels.SearchUserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchUserListViewModel::class)
    abstract fun bindUserListViewModel(userViewModel: SearchUserListViewModel): ViewModel
}
