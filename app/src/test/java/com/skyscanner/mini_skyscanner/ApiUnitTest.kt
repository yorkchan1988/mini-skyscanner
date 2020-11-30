package com.skyscanner.mini_skyscanner

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skyscanner.mini_skyscanner.di.AppModule
import com.skyscanner.mini_skyscanner.di.main.MainModule
import com.skyscanner.mini_skyscanner.models.FlightsApiResponse
import com.skyscanner.mini_skyscanner.models.Itinerary
import com.skyscanner.mini_skyscanner.models.Leg
import com.skyscanner.mini_skyscanner.network.api.FlightsApi
import com.skyscanner.mini_skyscanner.util.FileUtils
import io.reactivex.observers.TestObserver
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {

    lateinit var mockWebServer: MockWebServer
    val testObserver: TestObserver<FlightsApiResponse> = TestObserver()

    lateinit var flightsApi: FlightsApi

    private fun createMockResponse(code: Int, jsonFileName: String): MockResponse {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(code)
        mockResponse.setBody(FileUtils.readTestResourceFile(jsonFileName))
        return mockResponse
    }

    @Before
    fun setUp() {
        // start mock server
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val baseUrl: HttpUrl = mockWebServer.url("")
        val okHttpClient = AppModule.provideOkHttpClientInstance()
        val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        flightsApi = MainModule.provideFlightsApi(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getFlights_parseCorrectly() {
        // GIVEN
        val mockResponse = createMockResponse(HttpURLConnection.HTTP_OK, "sample_response.json")
        mockWebServer.enqueue(mockResponse)

        // WHEN
        flightsApi.getFlights().subscribe(testObserver)

        // THEN
        testObserver.assertNoErrors()

        val itinerary = Itinerary("it_1", listOf("leg_1"), "£35", "Wizzair.com", null)
        val leg = Leg("leg_1", "BUD","LTN","2020-10-31T15:35","2020-10-31T17:00",0, "Wizz Air","WZ",145)
        val apiResponse = FlightsApiResponse(listOf(itinerary), listOf(leg))
        testObserver.assertValue(apiResponse)
    }
}