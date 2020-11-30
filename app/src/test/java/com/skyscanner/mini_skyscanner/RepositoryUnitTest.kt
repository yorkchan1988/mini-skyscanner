package com.skyscanner.mini_skyscanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skyscanner.mini_skyscanner.di.AppModule
import com.skyscanner.mini_skyscanner.di.main.MainModule
import com.skyscanner.mini_skyscanner.models.FlightsApiResponse
import com.skyscanner.mini_skyscanner.models.Itinerary
import com.skyscanner.mini_skyscanner.models.Leg
import com.skyscanner.mini_skyscanner.network.ApiResource
import com.skyscanner.mini_skyscanner.network.api.FlightsApi
import com.skyscanner.mini_skyscanner.repository.FlightsRepository
import com.skyscanner.mini_skyscanner.util.FileUtils
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryUnitTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var flightsApi: FlightsApi

    lateinit var flightsRepository: FlightsRepository

    @Before
    fun setUp() {
        // create mock objects
        MockitoAnnotations.initMocks(this)
        flightsRepository = FlightsRepository(flightsApi)
    }

    @Test
    fun getInvestorProducts_correctInput() {
        // GIVEN
        val itinerary = Itinerary("it_1", listOf("leg_1"), "£35", "Wizzair.com", null)
        val leg = Leg("leg_1", "BUD","LTN","2020-10-31T15:35","2020-10-31T17:00",0, "Wizz Air","WZ",145)
        val apiResponse = FlightsApiResponse(listOf(itinerary), listOf(leg))
        Mockito.`when`(flightsApi.getFlights())
            .thenReturn(Observable.just(apiResponse))

        val transformedItinerary = Itinerary("it_1", listOf("leg_1"), "£35", "Wizzair.com", listOf(leg))

        // WHEN
        val result = flightsRepository.getFlights()
            .blockingLast()

        // THEN
        Assert.assertEquals(ApiResource.ApiStatus.SUCCESS, result.status)
        Assert.assertEquals(listOf(transformedItinerary), result.data)
    }
}