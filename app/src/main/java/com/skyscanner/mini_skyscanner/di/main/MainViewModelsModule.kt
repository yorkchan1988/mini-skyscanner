package com.skyscanner.mini_skyscanner.di.main

import androidx.lifecycle.ViewModel
import com.skyscanner.mini_skyscanner.di.ViewModelKey
import com.skyscanner.mini_skyscanner.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}