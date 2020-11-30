package com.skyscanner.mini_skyscanner.di

import androidx.lifecycle.ViewModelProvider
import com.skyscanner.mini_skyscanner.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory;
}