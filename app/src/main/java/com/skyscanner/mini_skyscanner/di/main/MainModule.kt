package com.skyscanner.mini_skyscanner.di.main

import com.example.minimoneybox.ui.main.useraccounts.FlightsRecyclerAdapter
import com.skyscanner.mini_skyscanner.network.api.FlightsApi
import com.skyscanner.mini_skyscanner.repository.FlightsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Module
    companion object {

        @MainScope
        @JvmStatic
        @Provides
        fun provideFlightsRecyclerAdapter(): FlightsRecyclerAdapter {
            return FlightsRecyclerAdapter()
        }

        @MainScope
        @JvmStatic
        @Provides
        fun provideFlightsApi(retrofit: Retrofit): FlightsApi {
            return retrofit.create(FlightsApi::class.java)
        }

        @MainScope
        @JvmStatic
        @Provides
        fun provideFlightsRepository(flightsApi: FlightsApi): FlightsRepository {
            return FlightsRepository(flightsApi)
        }
    }

}