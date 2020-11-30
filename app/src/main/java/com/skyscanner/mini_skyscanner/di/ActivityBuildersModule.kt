package com.skyscanner.mini_skyscanner.di

import com.skyscanner.mini_skyscanner.ui.MainActivity
import com.skyscanner.mini_skyscanner.di.main.MainFragmentBuildersModule
import com.skyscanner.mini_skyscanner.di.main.MainModule
import com.skyscanner.mini_skyscanner.di.main.MainScope
import com.skyscanner.mini_skyscanner.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}