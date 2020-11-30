package com.skyscanner.mini_skyscanner.network.api

import com.skyscanner.mini_skyscanner.models.FlightsApiResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface FlightsApi {

    @GET("/skyscanner-prod-takehome-test/flights.json")
    fun getInvestorProduct(): Observable<FlightsApiResponse>
}