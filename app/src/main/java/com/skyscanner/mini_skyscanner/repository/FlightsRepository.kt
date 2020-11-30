package com.skyscanner.mini_skyscanner.repository

import com.skyscanner.mini_skyscanner.models.FlightsApiResponse
import com.skyscanner.mini_skyscanner.models.Itinerary
import com.skyscanner.mini_skyscanner.network.ApiResource
import com.skyscanner.mini_skyscanner.network.api.FlightsApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class FlightsRepository @Inject constructor(private val flightsApi: FlightsApi) {
    companion object {
        private const val TAG = "FlightsRepository"
    }

    fun getFlights(): Observable<ApiResource<List<Itinerary>>> {
        return Observable.create { emitter ->
            // start to call api, change api status to loading
            emitter.onNext(ApiResource.Loading())

            flightsApi.getFlights()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { data ->
                        // api return success, return the parsed data object
                        val itineraries = data.itineraries
                        val legs = data.legs
                        itineraries.forEach { itinerary ->
                            itinerary.legs = itinerary.legIds.map { legId ->
                                legs.find { leg ->
                                    leg.lId == legId
                                }
                            }
                        }
                        emitter.onNext(ApiResource.Success(itineraries))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onError(error)
                        emitter.onComplete()
                    }
                )
        }
    }
}